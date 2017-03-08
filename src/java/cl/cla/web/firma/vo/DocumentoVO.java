/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.cla.web.firma.vo;

import java.io.Serializable;

/**
 *
 * @author oscar
 */
public class DocumentoVO implements Serializable {

    private int id;
    private boolean seleccionado;
    private String idItemOfertaEconomica;
    private String idOfertaEconomicaPadre;
    private String idActividad;
    private String idItemOfertaEconomPadre;
    private String idDocumento;
    private String nombreDocumento;
    private String flagFirmaDigital;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getIdItemOfertaEconomica() {
        return idItemOfertaEconomica;
    }

    public void setIdItemOfertaEconomica(String idItemOfertaEconomica) {
        this.idItemOfertaEconomica = idItemOfertaEconomica;
    }

    public String getIdOfertaEconomicaPadre() {
        return idOfertaEconomicaPadre;
    }

    public void setIdOfertaEconomicaPadre(String idOfertaEconomicaPadre) {
        this.idOfertaEconomicaPadre = idOfertaEconomicaPadre;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getIdItemOfertaEconomPadre() {
        return idItemOfertaEconomPadre;
    }

    public void setIdItemOfertaEconomPadre(String idItemOfertaEconomPadre) {
        this.idItemOfertaEconomPadre = idItemOfertaEconomPadre;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getFlagFirmaDigital() {
        return flagFirmaDigital;
    }

    public void setFlagFirmaDigital(String flagFirmaDigital) {
        this.flagFirmaDigital = flagFirmaDigital;
    }

}
