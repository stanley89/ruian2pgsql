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
package com.fordfrog.ruian2pgsql.containers;

import java.util.Date;
import org.postgresql.geometric.PGpoint;

/**
 * Container for AdresniMisto information.
 *
 * @author fordfrog
 */
public class AdresniMisto implements ItemWithDefinicniBod {

    private Integer kod;
    private boolean nespravny;
    private Integer adrpPsc;
    private Integer uliceKod;
    private Integer stavobjKod;
    private Integer cisloDomovni;
    private Integer cisloOrientacniHodnota;
    private String cisloOrientacniPismeno;
    private Long idTransRuian;
    private Date platiOd;
    private Boolean zmenaGrafiky;
    private Long nzIdGlobalni;
    private PGpoint definicniBod;

    public Integer getKod() {
        return kod;
    }

    public void setKod(final Integer kod) {
        this.kod = kod;
    }

    public boolean isNespravny() {
        return nespravny;
    }

    public void setNespravny(final boolean nespravny) {
        this.nespravny = nespravny;
    }

    public Integer getAdrpPsc() {
        return adrpPsc;
    }

    public void setAdrpPsc(final Integer adrpPsc) {
        this.adrpPsc = adrpPsc;
    }

    public Integer getUliceKod() {
        return uliceKod;
    }

    public void setUliceKod(final Integer uliceKod) {
        this.uliceKod = uliceKod;
    }

    public Integer getStavobjKod() {
        return stavobjKod;
    }

    public void setStavobjKod(final Integer stavobjKod) {
        this.stavobjKod = stavobjKod;
    }

    public Integer getCisloDomovni() {
        return cisloDomovni;
    }

    public void setCisloDomovni(final Integer cisloDomovni) {
        this.cisloDomovni = cisloDomovni;
    }

    public Integer getCisloOrientacniHodnota() {
        return cisloOrientacniHodnota;
    }

    public void setCisloOrientacniHodnota(
            final Integer cisloOrientacniHodnota) {
        this.cisloOrientacniHodnota = cisloOrientacniHodnota;
    }

    public String getCisloOrientacniPismeno() {
        return cisloOrientacniPismeno;
    }

    public void setCisloOrientacniPismeno(final String cisloOrientacniPismeno) {
        this.cisloOrientacniPismeno = cisloOrientacniPismeno;
    }

    public Long getIdTransRuian() {
        return idTransRuian;
    }

    public void setIdTransRuian(final Long idTransRuian) {
        this.idTransRuian = idTransRuian;
    }

    @SuppressWarnings("ReturnOfDateField")
    public Date getPlatiOd() {
        return platiOd;
    }

    @SuppressWarnings("AssignmentToDateFieldFromParameter")
    public void setPlatiOd(final Date platiOd) {
        this.platiOd = platiOd;
    }

    public Boolean getZmenaGrafiky() {
        return zmenaGrafiky;
    }

    public void setZmenaGrafiky(final Boolean zmenaGrafiky) {
        this.zmenaGrafiky = zmenaGrafiky;
    }

    public Long getNzIdGlobalni() {
        return nzIdGlobalni;
    }

    public void setNzIdGlobalni(final Long nzIdGlobalni) {
        this.nzIdGlobalni = nzIdGlobalni;
    }

    @Override
    public PGpoint getDefinicniBod() {
        return definicniBod;
    }

    @Override
    public void setDefinicniBod(final PGpoint definicniBod) {
        this.definicniBod = definicniBod;
    }
}