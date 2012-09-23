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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Curve.
 *
 * @author fordfrog
 */
public class Curve extends AbstractGeometry implements GeometryWithPoints, CurvedGeometry<Line> {

    /**
     * Circular string points.
     */
    private final List<Point> points = new ArrayList<>(5);

    @Override
    public void addPoint(final Point point) {
        points.add(point);
    }

    @Override
    public String toWKT() {
        final StringBuilder sbString = new StringBuilder(100);
        WKTUtils.appendSrid(sbString, getSrid());

        sbString.append("CIRCULARSTRING(");
        WKTUtils.appendPoints(sbString, points);
        sbString.append(')');

        return sbString.toString();
    }

    @Override
    public Line linearize(final double precision) {
        if (points.size() < 3) {
            throw new RuntimeException(MessageFormat.format(
                "Invalid Curve definition: need at least 3 control points, but got {0}.",
                points.size()));
        }

        final Line line = new Line();
        line.setSrid(getSrid());
        line.addPoint(points.get(0));
        for (int i = 2; i < points.size(); i = i + 2) {
            List<Point> linePoints = GeometryUtils.linearizeArc(precision,
                        points.get(i - 2), points.get(i - 1), points.get(i));
            for (final Point point : linePoints.subList(1, linePoints.size())) {
                line.addPoint(point);
            }
        }

        return line;
    }
}
