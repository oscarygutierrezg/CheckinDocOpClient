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
public class ResponseVO implements Serializable{

    private String codigo;
    private String mensaje;

    public ResponseVO() {
        codigo="-1";
        mensaje="";
    }

    public ResponseVO(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
