/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.cla.web.firma.integracion;

import cl.cla.web.firma.constantes.Constantes;
import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.DetalleDocumentoVO;
import cl.cla.web.firma.vo.ResponseVO;
import com.siebel.fins.OCSSpcConfirmacionSpcFirmaSpcDigitalSpcWF;
import com.siebel.fins.OcsConfirmarFirma;
import com.siebel.xml.ocs_20detalle_20documento_20io.Action;
import com.siebel.xml.ocs_20detalle_20documento_20io.ListOfAction;
import com.siebel.xml.ocs_20detalle_20documento_20io.ListOfOcsDetalleDocumentoIo;
import com.siebel.xml.ocs_20detalle_20documento_20io.ListOfOcsDetalleDocumentoIoTopElmt;
import java.util.Map;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

/**
 *
 * @author oscar
 */
public class ConfirmFirmaOPIntegracion {

    public ResponseVO confirmFirmaINOperation(DetalleDocumentoVO detalleDocumento) throws Exception {
        ResponseVO responseVO = new ResponseVO();
        try {
            OcsConfirmarFirma service = new OcsConfirmarFirma();
            OCSSpcConfirmacionSpcFirmaSpcDigitalSpcWF port = service.getOCSSpcConfirmacionSpcFirmaSpcDigitalSpcWF();

            BindingProvider bp = (BindingProvider) port;
            Map<String, Object> rc = bp.getRequestContext();
            rc.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://lasiewebsit.ccaf.andes:8080/enterprise/EntDetalleDocumentoService?WSDL");

            ListOfOcsDetalleDocumentoIoTopElmt requestIO = new ListOfOcsDetalleDocumentoIoTopElmt();
            ListOfOcsDetalleDocumentoIo value = new ListOfOcsDetalleDocumentoIo();
            ListOfAction listOfAction = new ListOfAction();
            Action action = new Action();
            action.setId("1-1BSUGWR");
            action.setOCSIdDocumento("1-1BSUHIF");
            listOfAction.getAction().add(action);
            value.setListOfAction(listOfAction);
            requestIO.setListOfOcsDetalleDocumentoIo(value);
            Holder<String> errorSpcCode = new Holder<String>();
            Holder<String> errorSpcMessage = new Holder<String>();
            Holder<String> executionStatus = new Holder<String>();

            port.ocsSpcConfirmacionSpcFirmaSpcDigitalSpcWF(requestIO, errorSpcCode, errorSpcMessage, executionStatus);
            System.out.println("response.getEstado().getCodigo() " + errorSpcCode.value);
            if (errorSpcCode.value.compareTo(Constantes.codigoOK) == 0) {

                System.out.println("Codigo " + errorSpcCode.value);
                System.out.println("Mensaje " + executionStatus.value);

                responseVO = new ResponseVO(errorSpcCode.value, errorSpcMessage.value);

            } else {
                throw new FirmaException(errorSpcMessage.value);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("Error procesando el servicio firmar documento");
        }
        return responseVO;
    }

}
