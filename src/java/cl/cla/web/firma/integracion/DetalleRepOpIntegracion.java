package cl.cla.web.firma.integracion;

import cl.cla.web.firma.constantes.Constantes;
import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.DetalleDocumentoVO;
import cl.cla.web.firma.vo.ResponseVO;
import com.siebel.fins.OCSDetalledeDocumentosWS;
import com.siebel.fins.OcsDetalleDocumento;
import com.siebel.xml.ocs_20detalle_20documento_20io.Action;
import com.siebel.xml.ocs_20detalle_20documento_20io.ListOfOcsDetalleDocumentoIoTopElmt;
import com.siebel.xml.ocs_20detalle_20documento_20io.ReportOutputBC;
import java.util.Map;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

public class DetalleRepOpIntegracion {

    public DetalleDocumentoVO detalleRepINOperation(String idActividad) throws FirmaException, Exception {
        DetalleDocumentoVO detalleDocumento = null;
        try {
            OcsDetalleDocumento service = new OcsDetalleDocumento();
            OCSDetalledeDocumentosWS port = service.getOCSDetalledeDocumentosWS();

            BindingProvider bp = (BindingProvider) port;
            Map<String, Object> rc = bp.getRequestContext();
            rc.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://lasiewebsit.ccaf.andes:8080/enterprise/EntDetalleDocumentoService?WSDL");

            String actionId = idActividad;
            Holder<String> errorCode = new Holder<String>();

            Holder<String> errorMessage = new Holder<String>();
            Holder<ListOfOcsDetalleDocumentoIoTopElmt> responseSpcIO = new Holder<ListOfOcsDetalleDocumentoIoTopElmt>();
            port.ocsSpcDetalleSpcdeSpcDocumentosSpcWF(actionId, errorCode, errorMessage, responseSpcIO);

            System.out.println("response.getEstado().getCodigo() " + errorCode.value);
            if (errorCode.value.compareTo(Constantes.codigoOK) == 0) {
                detalleDocumento = new DetalleDocumentoVO();
                Action action = responseSpcIO.value.getListOfOcsDetalleDocumentoIo().getListOfAction().getAction().get(0);
                ReportOutputBC reportOutputBC=action.getListOfReportOutputBC().getReportOutputBC().get(0);
                detalleDocumento.setArchivo(reportOutputBC.getReportOutputFileBuffer());
                detalleDocumento.setExtension(reportOutputBC.getReportOutputFileExt());

                detalleDocumento.setId(reportOutputBC.getId());
                detalleDocumento.setIdActividad(action.getId());
                detalleDocumento.setIdDocumento(action.getOCSIdDocumento());
                detalleDocumento.setNombre(reportOutputBC.getReportName());
                detalleDocumento.setTamano(reportOutputBC.getReportOutputFileSize());

                System.out.println("Nombre " + reportOutputBC.getReportName());
                System.out.println("Extension " + reportOutputBC.getReportOutputFileExt());
                ResponseVO responseVO = new ResponseVO(errorCode.value, errorMessage.value);
                detalleDocumento.setResponse(responseVO);

            } else {
                throw new FirmaException(errorMessage.value);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("Error procesando el servicio consultar detalle documento");
        }
        return detalleDocumento;

    }
}
