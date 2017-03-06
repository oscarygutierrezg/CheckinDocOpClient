/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.cla.web.firma.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class ListarDocumentosVO implements Serializable{

    private ResponseVO response;
    private List<DocumentoVO> documentos;

    public ListarDocumentosVO() {
        response = new ResponseVO();
        this.documentos= new ArrayList<>();
    }

    public ResponseVO getResponse() {
        return response;
    }

    public void setResponse(ResponseVO response) {
        this.response = response;
    }

   
    public List<DocumentoVO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoVO> documentos) {

        this.documentos = documentos;
    }
    
    

}
