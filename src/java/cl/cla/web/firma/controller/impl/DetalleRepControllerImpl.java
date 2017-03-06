package cl.cla.web.firma.controller.impl;

import cl.cla.web.firma.integracion.DetalleRepOpIntegracion;
import cl.cla.web.firma.controller.DetalleRepController;
import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.DetalleDocumentoVO;

public class DetalleRepControllerImpl implements DetalleRepController{
    
    DetalleRepOpIntegracion integracion;

    public DetalleRepControllerImpl() {
        integracion= new DetalleRepOpIntegracion();
    }
    
    

    @Override 
    public DetalleDocumentoVO detalleRepINOperation(String idActividad) throws FirmaException{
        try {
            return integracion.detalleRepINOperation(idActividad);
        } catch (Exception e) {
            throw new FirmaException(e);
        }

    }
}
