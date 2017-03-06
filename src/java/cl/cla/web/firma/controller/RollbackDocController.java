package cl.cla.web.firma.controller;

import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.ResponseVO;
import cl.cla.web.firma.vo.RollbackDocumento;

public interface RollbackDocController {

    public ResponseVO rollbackDocINOperation(RollbackDocumento rollbackDocumento)  throws FirmaException;

}
