/**
 * Copyright 2012 Miroslav Šulc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.fordfrog.ruian2pgsql.gml;

import com.fordfrog.ruian2pgsql.Config;
import com.fordfrog.ruian2pgsql.utils.Log;
import com.fordfrog.ruian2pgsql.utils.Namespaces;
import com.fordfrog.ruian2pgsql.utils.XMLUtils;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.text.MessageFormat;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * GML utilities.
 *
 * @author fordfrog
 */
public class GMLUtils {

    /**
     * Whether to enable multipoint bug workaround.
     */
    private static boolean multipointBugWorkaround;

    /**
     * Getter for {@link #multipointBugWorkaround}.
     *
     * @return {@link #multipointBugWorkaround}
     */
    public static boolean isMultipointBugWorkaround() {
        return multipointBugWorkaround;
    }

    /**
     * Setter for {@link #multipointBugWorkaround}.
     *
     * @param multipointBugWorkaround {@link #multipointBugWorkaround}
     */
    public static void setMultipointBugWorkaround(
            final boolean multipointBugWorkaround) {
        GMLUtils.multipointBugWorkaround = multipointBugWorkaround;
    }

    /**
     * Checks whether installed version of Postgis is affected by bug
     * http://trac.osgeo.org/postgis/ticket/1928.
     *
     * @param con database connection
     *
     * @return true if it is affected, otherwise false
     */
    public static boolean checkMultipointBug(final Connection con) {
        try (final Statement stm = con.createStatement();
                final ResultSet rs = stm.executeQuery(
                        "SELECT st_astext(st_geomfromgml('<gml:MultiPoint "
                        + "xmlns:gml=\"http://www.opengis.net/gml/3.2\" "
                        + "gml:id=\"DOB.545058.X\" "
                        + "srsName=\"urn:ogc:def:crs:EPSG::2065\" "
                        + "srsDimension=\"2\"><gml:pointMembers>"
                        + "<gml:Point gml:id=\"DOB.545058.1\">"
                        + "<gml:pos>496547.00 1139895.00</gml:pos>"
                        + "</gml:Point></gml:pointMembers>"
                        + "</gml:MultiPoint>'));")) {
            rs.next();

            return "MULTIPOINT Z EMPTY".equals(rs.getString(1));
        } catch (final SQLException ex) {
            throw new RuntimeException(
                    "Failed to check multipoint bug presence", ex);
        }
    }

    /**
     * Creates GML string from XML element and its sub-elements.
     *
     * @param reader XML stream reader
     * @param con    database connection
     *
     * @return element tree as string
     *
     * @throws XMLStreamException Thrown if problem occurred while reading or
     *                            writing XML stream.
     */
    public static String createGMLString(final XMLStreamReader reader,
            final Connection con) throws XMLStreamException {
        final XMLOutputFactory xMLOutputFactory =
                XMLOutputFactory.newInstance();
        final StringWriter stringWriter = new StringWriter(1_024);
        final XMLStreamWriter writer =
                xMLOutputFactory.createXMLStreamWriter(stringWriter);

        writer.writeStartDocument();
        writer.setPrefix("gml", Namespaces.GML);

        writeElementTree(reader, writer, true, false);

        writer.writeEndDocument();
        writer.close();

        final String result =
                XMLUtils.stripDeclaration(stringWriter.toString());

        if (Config.isIgnoreInvalidGML()) {
            return isValidGML(con, result) ? result : null;
        } else {
            return result;
        }
    }

    /**
     * Writes the element tree.
     *
     * @param reader       XML stream reader
     * @param writer       XML stream writer
     * @param setNamespace whether GML namespace should be set on the element
     * @param isMultipoint whether any of the parents is gml:Multipoint element
     *
     * @throws XMLStreamException Thrown if problem occurred while reading or
     *                            writing XML stream.
     */
    private static void writeElementTree(final XMLStreamReader reader,
            final XMLStreamWriter writer, final boolean setNamespace,
            final boolean isMultipoint) throws XMLStreamException {
        final String namespace = reader.getNamespaceURI();
        final String localName = reader.getLocalName();
        final boolean curIsMultipoint = Namespaces.GML.equals(namespace)
                && "MultiPoint".equals(localName);
        final boolean isIgnorePointMembers = multipointBugWorkaround
                && isMultipoint && Namespaces.GML.equals(namespace)
                && "pointMembers".equals(localName);
        final boolean isMultipointPoint = multipointBugWorkaround
                && isMultipoint && Namespaces.GML.equals(namespace)
                && "Point".equals(localName);

        if (isIgnorePointMembers) {
            // we do not write pointMembers, instead pointMember is written
            // along with the Point itself
        } else if (isMultipointPoint) {
            writer.writeStartElement(Namespaces.GML, "pointMember");
            writeStartElement(reader, writer, setNamespace);
        } else {
            writeStartElement(reader, writer, setNamespace);
        }

        while (reader.hasNext()) {
            final int event = reader.next();

            switch (event) {
                case XMLStreamReader.START_ELEMENT:
                    writeElementTree(reader, writer, false,
                            curIsMultipoint || isMultipoint);
                    break;
                case XMLStreamReader.END_ELEMENT:
                    if (!isIgnorePointMembers) {
                        writer.writeEndElement();
                    }

                    if (isMultipointPoint) {
                        writer.writeEndElement();
                    }
                    return;
                case XMLStreamReader.CHARACTERS:
                    writer.writeCharacters(reader.getText());
                    break;
                default:
                    throw new RuntimeException(
                            "Unsupported XML event " + event);
            }
        }
    }

    /**
     * Writes start element and its attributes.
     *
     * @param reader       XML stream reader
     * @param writer       XML stream writer
     * @param setNamespace whether GML namespace should be set
     *
     * @throws XMLStreamException Thrown if problem occurred while working with
     *                            XML streams.
     */
    private static void writeStartElement(final XMLStreamReader reader,
            final XMLStreamWriter writer, final boolean setNamespace)
            throws XMLStreamException {
        writer.writeStartElement(
                reader.getNamespaceURI(), reader.getLocalName());

        if (setNamespace) {
            writer.writeAttribute("xmlns:gml", Namespaces.GML);
        }

        for (int i = 0; i < reader.getAttributeCount(); i++) {
            final String attrNamespace = reader.getAttributeNamespace(i);

            if (attrNamespace == null) {
                writer.writeAttribute(reader.getAttributeLocalName(i),
                        reader.getAttributeValue(i));
            } else {
                writer.writeAttribute(reader.getAttributeNamespace(i),
                        reader.getAttributeLocalName(i),
                        reader.getAttributeValue(i));
            }
        }
    }

    /**
     * Checks whether specified GML string is valid for ST_GeomFromGML()
     * function.
     *
     * @param con database connection
     * @param gml GML string
     *
     * @return true if GML string is valid, otherwise false
     */
    private static boolean isValidGML(final Connection con, final String gml) {
        try (final PreparedStatement pstm = con.prepareStatement(
                        "SELECT st_geomfromgml(?)")) {
            final Savepoint savepoint = con.setSavepoint("gml_check");

            pstm.setString(1, gml);

            try (final ResultSet rs = pstm.executeQuery()) {
                con.releaseSavepoint(savepoint);
            } catch (final SQLException ex) {
                Log.write(MessageFormat.format("Invalid GML: {0}", gml));
                con.rollback(savepoint);

                return false;
            }
        } catch (final SQLException ex) {
            throw new RuntimeException(
                    "Failed to validate GML string: " + gml, ex);
        }

        return true;
    }

    /**
     * Creates new instance of GMLUtils.
     */
    private GMLUtils() {
    }
}
