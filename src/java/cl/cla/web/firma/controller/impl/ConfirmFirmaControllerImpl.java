/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.cla.web.firma.controller.impl;

import cl.cla.web.firma.integracion.ConfirmFirmaOPIntegracion;
import cl.cla.web.firma.controller.ConfirmFirmaController;
import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.DetalleDocumentoVO;
import cl.cla.web.firma.vo.ResponseVO;

/**
 *
 * @author oscar
 */
public class ConfirmFirmaControllerImpl implements ConfirmFirmaController {

    private ConfirmFirmaOPIntegracion integracion;

    public ConfirmFirmaControllerImpl() {
        integracion = new ConfirmFirmaOPIntegracion();
    }

    @Override
    public ResponseVO confirmFirmaINOperation(DetalleDocumentoVO detalleDocumento) throws FirmaException {
        try {
            return integracion.confirmFirmaINOperation(detalleDocumento);
        } catch (Exception e) {
            throw new FirmaException(e);
        }
    }

}
