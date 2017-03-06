/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.cla.web.firma.controller;

import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.DetalleDocumentoVO;
import cl.cla.web.firma.vo.ResponseVO;

/**
 *
 * @author oscar
 */
public interface ConfirmFirmaController {

    public ResponseVO confirmFirmaINOperation(DetalleDocumentoVO detalleDocumento)  throws FirmaException;


}
