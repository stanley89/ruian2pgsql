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

/**
 * Container for Zsj information.
 *
 * @author fordfrog
 */
public class Zsj implements ItemWithDefinicniBod, ItemWithHranice,
        ItemWithMluvCharPad {

    private Integer kod;
    private String nazev;
    private Boolean nespravny;
    private Integer katuzKod;
    private Integer charakterZsjKod;
    private String mluvCharPad2;
    private String mluvCharPad3;
    private String mluvCharPad4;
    private String mluvCharPad5;
    private String mluvCharPad6;
    private String mluvCharPad7;
    private Long vymera;
    private Date platiOd;
    private Boolean zmenaGrafiky;
    private Long nzIdGlobalni;
    private Long idTransRuian;
    private String definicniBod;
    private String hranice;
    private Date datumVzniku;

    public Integer getKod() {
        return kod;
    }

    public void setKod(final Integer kod) {
        this.kod = kod;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(final String nazev) {
        this.nazev = nazev;
    }

    public Boolean getNespravny() {
        return nespravny;
    }

    public void setNespravny(final Boolean nespravny) {
        this.nespravny = nespravny;
    }

    public Integer getKatuzKod() {
        return katuzKod;
    }

    public void setKatuzKod(final Integer katuzKod) {
        this.katuzKod = katuzKod;
    }

    public Integer getCharakterZsjKod() {
        return charakterZsjKod;
    }

    public void setCharakterZsjKod(final Integer charakterZsjKod) {
        this.charakterZsjKod = charakterZsjKod;
    }

    @Override
    public String getMluvCharPad2() {
        return mluvCharPad2;
    }

    @Override
    public void setMluvCharPad2(final String mluvCharPad2) {
        this.mluvCharPad2 = mluvCharPad2;
    }

    @Override
    public String getMluvCharPad3() {
        return mluvCharPad3;
    }

    @Override
    public void setMluvCharPad3(final String mluvCharPad3) {
        this.mluvCharPad3 = mluvCharPad3;
    }

    @Override
    public String getMluvCharPad4() {
        return mluvCharPad4;
    }

    @Override
    public void setMluvCharPad4(final String mluvCharPad4) {
        this.mluvCharPad4 = mluvCharPad4;
    }

    @Override
    public String getMluvCharPad5() {
        return mluvCharPad5;
    }

    @Override
    public void setMluvCharPad5(final String mluvCharPad5) {
        this.mluvCharPad5 = mluvCharPad5;
    }

    @Override
    public String getMluvCharPad6() {
        return mluvCharPad6;
    }

    @Override
    public void setMluvCharPad6(final String mluvCharPad6) {
        this.mluvCharPad6 = mluvCharPad6;
    }

    @Override
    public String getMluvCharPad7() {
        return mluvCharPad7;
    }

    @Override
    public void setMluvCharPad7(final String mluvCharPad7) {
        this.mluvCharPad7 = mluvCharPad7;
    }

    public Long getVymera() {
        return vymera;
    }

    public void setVymera(final Long vymera) {
        this.vymera = vymera;
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

    public Long getIdTransRuian() {
        return idTransRuian;
    }

    public void setIdTransRuian(final Long idTransRuian) {
        this.idTransRuian = idTransRuian;
    }

    @Override
    public String getDefinicniBod() {
        return definicniBod;
    }

    @Override
    public void setDefinicniBod(final String definicniBod) {
        this.definicniBod = definicniBod;
    }

    @Override
    public String getHranice() {
        return hranice;
    }

    @Override
    public void setHranice(final String hranice) {
        this.hranice = hranice;
    }

    public Date getDatumVzniku() {
        return datumVzniku;
    }

    public void setDatumVzniku(final Date datumVzniku) {
        this.datumVzniku = datumVzniku;
    }
}
