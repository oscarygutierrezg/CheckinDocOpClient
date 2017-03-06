package cl.cla.web.firma.controller;

import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.CheckinDocumentoVO;
import cl.cla.web.firma.vo.ResponseVO;

public interface CheckinDocController {

    public ResponseVO checkinDocINOperation(CheckinDocumentoVO checkinDocumento) throws FirmaException;

}
