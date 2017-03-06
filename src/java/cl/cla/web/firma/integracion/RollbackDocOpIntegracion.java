package cl.cla.web.firma.integracion;

import cl.cla.osb.RollbackDocOp;
import cl.cla.osb.RollbackDocOp_Service;
import cl.cla.osb.RequestEAI119RollbackDocumento;
import cl.cla.osb.ResponseEAI119RollbackDocumento;
import cl.cla.web.firma.constantes.Constantes;
import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.util.DateUtil;
import cl.cla.web.firma.vo.ResponseVO;
import cl.cla.web.firma.vo.RollbackDocumento;
import java.util.Date;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class RollbackDocOpIntegracion {

    public ResponseVO rollbackDocINOperation(RollbackDocumento rollbackDocumento) throws Exception {
        ResponseVO responseVO = new ResponseVO();
        try {
            RollbackDocOp_Service service = new RollbackDocOp_Service();
            RollbackDocOp port = service.getRollbackDocOpPort();

            BindingProvider bp = (BindingProvider) port;
            Map<String, Object> rc = bp.getRequestContext();
            rc.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://lasiewebsit.ccaf.andes:8080/enterprise/EntRollbackDocumentoService?WSDL");

            RequestEAI119RollbackDocumento request = new RequestEAI119RollbackDocumento();
            RequestEAI119RollbackDocumento.Header header = new RequestEAI119RollbackDocumento.Header();
            header.setCanal("SIEBEL");
            header.setFechaTransaccion(DateUtil.dateToxmlGregorianCalendar(new Date()));
            header.setIdTransaccion("SBL434234234");
            header.setUsuario("EJFRONT01");
            RequestEAI119RollbackDocumento.Documento.InformacionCabecera informacionCabecera = new RequestEAI119RollbackDocumento.Documento.InformacionCabecera();
            informacionCabecera.setGrupoSeguridad(rollbackDocumento.getGrupoSeguridad());
            informacionCabecera.setNombre(rollbackDocumento.getGrupoSeguridad());
            informacionCabecera.setTipo(rollbackDocumento.getTipo());
            RequestEAI119RollbackDocumento.Documento.Metadata metadata = new RequestEAI119RollbackDocumento.Documento.Metadata();
            metadata.setFechaVencimiento(DateUtil.dateToxmlGregorianCalendar(rollbackDocumento.getFechaVencimiento()));
            RequestEAI119RollbackDocumento.Documento documento = new RequestEAI119RollbackDocumento.Documento();
            documento.setInformacionCabecera(informacionCabecera);
            documento.setMetadata(metadata);
            request.setHeader(header);
            request.setDocumento(documento);
            ResponseEAI119RollbackDocumento response = port.rollbackDocINOperation(request);
            System.out.println("response " + response);
            System.out.println("Codigo " + response.getEstado().getCodigo());
            System.out.println("Mensaje " + response.getEstado().getMensaje());
            if (response != null && response.getEstado() != null) {
                System.out.println("Codigo " + response.getEstado().getCodigo());
                System.out.println("Mensaje " + response.getEstado().getMensaje());
                if (response.getEstado().getCodigo().compareTo(Constantes.codigoOK) == 0) {
                    responseVO = new ResponseVO(response.getEstado().getCodigo(), response.getEstado().getMensaje());
                } else {
                    throw new FirmaException(response.getEstado().getMensaje());
                }
            } else {
                throw new Exception("Error invocando el servicio rollback documento");
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("Error procesando el servicio rollback documento");
        }
        return responseVO;
    }

    public static void main(String[] args) throws Exception {
        RollbackDocumento rollbackDocumento = new RollbackDocumento();
        rollbackDocumento.setFechaVencimiento(new Date());
        rollbackDocumento.setGrupoSeguridad("Oferta");
        rollbackDocumento.setNombre("WCC_007012");
        rollbackDocumento.setTipo("Otros");
        RollbackDocOpIntegracion docOpIntegracion = new RollbackDocOpIntegracion();
        docOpIntegracion.rollbackDocINOperation(rollbackDocumento);
    }

}
