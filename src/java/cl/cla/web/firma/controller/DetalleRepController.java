package cl.cla.web.firma.controller;

import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.DetalleDocumentoVO;

public interface DetalleRepController {

    public DetalleDocumentoVO detalleRepINOperation(String idActividad)  throws FirmaException;

}
