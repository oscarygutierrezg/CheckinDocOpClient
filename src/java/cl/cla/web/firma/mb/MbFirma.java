/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.cla.web.firma.mb;

import cl.cla.web.firma.controller.DetalleRepController;
import cl.cla.web.firma.controller.ListarDocumentosController;
import cl.cla.web.firma.controller.impl.DetalleRepControllerImpl;
import cl.cla.web.firma.controller.impl.ListarDocumentosControllerImpl;
import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.DetalleDocumentoVO;
import cl.cla.web.firma.vo.DocumentoVO;
import cl.cla.web.firma.vo.ListarDocumentosVO;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author oscar
 */
public class MbFirma implements Serializable {

    private ListarDocumentosController documentosController;
    private DetalleRepController detalleRepController;
    private List<DocumentoVO> documentos;
    private boolean errorCargaDocumentos;
    private String idOferta;
    private DetalleDocumentoVO detalleDocumentoVO;

    private String run;
    private String nombres;
    private String apellidos;
    private String genero;
    private String nacionalidad;
    private String fechaNacimiento;
    private String fechaVencimiento;
    private String numeroSerie;
    private String numeroTransaccion;
    private String resultadoTransaccion;
    private String ciVencida;
    private String tipoCedula;
    private String estadoInicioApplet;
    private String estadoRegistro;
    private String estadoCargaVista;

    public MbFirma() {
        documentosController = new ListarDocumentosControllerImpl();
        detalleRepController = new DetalleRepControllerImpl();
    }

    public void consultarDocumentos() {
        System.out.println("consultarDocumentos");
        if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
            return; // Skip ajax requests.
        }
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        String idOferta = ec.getRequestParameterMap().get("formDocumentos:idOferta");
        try {
            System.out.println("idOferta " + idOferta);

            ListarDocumentosVO listarDocumentosVO = documentosController.listDocsINOperation(idOferta);
            if (listarDocumentosVO != null) {
                documentos = listarDocumentosVO.getDocumentos();
                
                HttpSession session = (HttpSession) ec.getSession(true);
                String idActividad =(String) session.getAttribute("idActividad");
                if(idActividad!=null){
                    for (DocumentoVO documento : documentos) {
                        if(documento.getIdActividad().compareTo(idActividad)==0){
                            documento.setSeleccionado(true);
                        }
                    }
                }
            }

        } catch (FirmaException ex) {
            ex.printStackTrace();
            Logger.getLogger(MbFirma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String varData1() {
        System.out.println("varData1");
        return "index?faces-redirect=true";
    }
    
      public String varData2() {
        System.out.println("varData2");
        return "index?faces-redirect=true";
    }

    public String varData() {
        System.out.println("run " + run);
        System.out.println("nombres " + nombres);
        System.out.println("apellidos " + apellidos);
        System.out.println("genero " + genero);
        System.out.println("nacionalidad " + nacionalidad);
        System.out.println("fechaNacimiento " + fechaNacimiento);
        System.out.println("fechaVencimiento " + fechaVencimiento);
        System.out.println("numeroSerie " + numeroSerie);
        System.out.println("numeroTransaccion " + numeroTransaccion);
        System.out.println("resultadoTransaccion " + resultadoTransaccion);
        System.out.println("ciVencida " + ciVencida);
        System.out.println("tipoCedula " + tipoCedula);
        return "index?faces-redirect=true";
    }

    public String consultarDocumento(String idActividad) {
        try {
            System.out.println("idActividad " + idActividad);

            detalleDocumentoVO = detalleRepController.detalleRepINOperation(idActividad);
            FacesContext context2 = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context2.getExternalContext().getSession(true);
            session.setAttribute("idActividad", idActividad);

        } catch (FirmaException ex) {
            ex.printStackTrace();
            Logger.getLogger(MbFirma.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index?faces-redirect=true";
    }

    public String getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(String idOferta) {
        this.idOferta = idOferta;
    }

    public boolean isErrorCargaDocumentos() {
        return errorCargaDocumentos;
    }

    public void setErrorCargaDocumentos(boolean errorCargaDocumentos) {
        this.errorCargaDocumentos = errorCargaDocumentos;
    }

    public List<DocumentoVO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoVO> documentos) {
        this.documentos = documentos;
    }

    public DetalleDocumentoVO getDetalleDocumentoVO() {
        return detalleDocumentoVO;
    }

    public void setDetalleDocumentoVO(DetalleDocumentoVO detalleDocumentoVO) {
        this.detalleDocumentoVO = detalleDocumentoVO;
    }

    public ListarDocumentosController getDocumentosController() {
        return documentosController;
    }

    public void setDocumentosController(ListarDocumentosController documentosController) {
        this.documentosController = documentosController;
    }

    public DetalleRepController getDetalleRepController() {
        return detalleRepController;
    }

    public void setDetalleRepController(DetalleRepController detalleRepController) {
        this.detalleRepController = detalleRepController;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getNumeroTransaccion() {
        return numeroTransaccion;
    }

    public void setNumeroTransaccion(String numeroTransaccion) {
        this.numeroTransaccion = numeroTransaccion;
    }

    public String getResultadoTransaccion() {
        return resultadoTransaccion;
    }

    public void setResultadoTransaccion(String resultadoTransaccion) {
        this.resultadoTransaccion = resultadoTransaccion;
    }

    public String getCiVencida() {
        return ciVencida;
    }

    public void setCiVencida(String ciVencida) {
        this.ciVencida = ciVencida;
    }

    public String getTipoCedula() {
        return tipoCedula;
    }

    public void setTipoCedula(String tipoCedula) {
        this.tipoCedula = tipoCedula;
    }

    public String getEstadoInicioApplet() {
        return estadoInicioApplet;
    }

    public void setEstadoInicioApplet(String estadoInicioApplet) {
        this.estadoInicioApplet = estadoInicioApplet;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoCargaVista() {
        return estadoCargaVista;
    }

    public void setEstadoCargaVista(String estadoCargaVista) {
        this.estadoCargaVista = estadoCargaVista;
    }

}
