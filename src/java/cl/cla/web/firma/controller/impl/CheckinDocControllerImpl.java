package cl.cla.web.firma.controller.impl;

import cl.cla.web.firma.integracion.CheckinDocOpIntegracion;
import cl.cla.web.firma.controller.CheckinDocController;
import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.CheckinDocumentoVO;
import cl.cla.web.firma.vo.ResponseVO;

public class CheckinDocControllerImpl implements CheckinDocController {

    private CheckinDocOpIntegracion integracion;

    public CheckinDocControllerImpl() {
        integracion = new CheckinDocOpIntegracion();

    }

    @Override
    public ResponseVO checkinDocINOperation(CheckinDocumentoVO checkinDocumento) throws FirmaException {
        try {
            return integracion.checkinDocINOperation(checkinDocumento);
        } catch (Exception e) {
            throw new FirmaException(e);
        }
    }
}
