package cl.cla.web.firma.controller;

import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.ListarDocumentosVO;

public interface ListarDocumentosController {

    public ListarDocumentosVO listDocsINOperation(String idOferta)  throws FirmaException;


}
