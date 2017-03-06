package cl.cla.web.firma.controller.impl;

import cl.cla.web.firma.controller.RollbackDocController;
import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.integracion.RollbackDocOpIntegracion;
import cl.cla.web.firma.vo.ResponseVO;
import cl.cla.web.firma.vo.RollbackDocumento;

public class RollbackDocControllerImpl implements RollbackDocController {

    RollbackDocOpIntegracion integracion;

    public RollbackDocControllerImpl() {
        integracion = new RollbackDocOpIntegracion();
    }

    @Override
    public ResponseVO rollbackDocINOperation(RollbackDocumento rollbackDocumento) throws FirmaException {
        try {
            return integracion.rollbackDocINOperation(rollbackDocumento);
        } catch (Exception e) {
            throw new FirmaException(e);
        }
    }

}
