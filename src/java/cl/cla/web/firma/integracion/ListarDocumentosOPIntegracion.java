package cl.cla.web.firma.integracion;

import cl.cla.web.firma.constantes.Constantes;
import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.DocumentoVO;
import cl.cla.web.firma.vo.ListarDocumentosVO;
import cl.cla.web.firma.vo.ResponseVO;
import com.siebel.fins.OCSListadoDocumentosWS;
import com.siebel.fins.OcSListadoDeDocumentos;
import com.siebel.xml.ocs_20listado_20de_20reportes_20io.Action;
import com.siebel.xml.ocs_20listado_20de_20reportes_20io.ListOfOcsListadoDeReportesIoTopElmt;
import com.siebel.xml.ocs_20listado_20de_20reportes_20io.QuoteItem;
import java.util.Map;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

public class ListarDocumentosOPIntegracion {

    public ListarDocumentosVO listDocsINOperation(String idOferta) throws Exception {
        ListarDocumentosVO documentosVO = null;
        try {
            OcSListadoDeDocumentos service = new OcSListadoDeDocumentos();
            OCSListadoDocumentosWS port = service.getOCSListadoDocumentosWS();

            BindingProvider bp = (BindingProvider) port;
            Map<String, Object> rc = bp.getRequestContext();
            rc.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://lasiewebsit.ccaf.andes:8080/enterprise/EntListadoDocumentosService?WSDL");

            String quoteId ="1-1BSUGQU";
            Holder<String> ocsTemplateFirmaDigId = new Holder<String>();
            ocsTemplateFirmaDigId.value = "1-1BSUGWM";
            Holder<String> errorSpcCode = new Holder<String>();

            Holder<String> errorSpcMessage = new Holder<String>();
            Holder<ListOfOcsListadoDeReportesIoTopElmt> responseSpcIO = new Holder<ListOfOcsListadoDeReportesIoTopElmt>();
            port.ocsSpcListadoSpcdeSpcDocumentosSpcWF(quoteId, ocsTemplateFirmaDigId, errorSpcCode, errorSpcMessage, responseSpcIO);

            System.out.println("Codigo " + errorSpcCode.value);
            System.out.println("Mensaje " + errorSpcMessage.value);

            if (errorSpcCode.value.compareTo(Constantes.codigoOK) == 0) {
                documentosVO = new ListarDocumentosVO();
                ResponseVO responseVO = new ResponseVO(errorSpcCode.value, errorSpcMessage.value);
                documentosVO.setResponse(responseVO);
                QuoteItem quoteItem=responseSpcIO.value.getListOfOcsListadoDeReportesIo().getQuote().get(0).getListOfQuoteItem().getQuoteItem().get(0);
                for (Action action : quoteItem.getListOfAction().getAction()) {
                    DocumentoVO documentoVO = new DocumentoVO();
                    documentoVO.setIdItemOfertaEconomica(quoteItem.getId());
                    documentoVO.setIdOfertaEconomicaPadre(quoteItem.getQuoteId());
                    
                    
                    documentoVO.setFlagFirmaDigital(action.getOCSFirmaDigitalFlag());
                    documentoVO.setIdActividad(action.getId());
                    documentoVO.setIdDocumento(action.getOCSIdDocumento());
                    documentoVO.setIdItemOfertaEconomPadre(action.getParentActivityId());
                    documentoVO.setNombreDocumento(action.getDescription());
                    documentosVO.getDocumentos().add(documentoVO);
                }
            } else {
                throw new FirmaException(errorSpcMessage.value);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("Error procesando el servicio listar documentos");
        }
        if(documentosVO!=null){
            System.out.println("documentosVO "+documentosVO.getDocumentos().size());
        }
        return documentosVO;
    }

}
