/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.cla.web.firma.servlet;

/**
 *
 * @author amunozme
 */
import cl.cla.web.firma.controller.impl.DetalleRepControllerImpl;
import cl.cla.web.firma.controller.DetalleRepController;
import cl.cla.web.firma.exception.FirmaException;
import cl.cla.web.firma.vo.DetalleDocumentoVO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class ServletDetalleDocumentoPdf extends HttpServlet {

    private DetalleRepController detalleRepController;

    public void init() {
        detalleRepController = new DetalleRepControllerImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        generarPdf(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        generarPdf(request, response);
    }

    private void generarPdf(HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        String p = request.getParameter("p") != null ? request.getParameter("p") : "";
        System.out.println("p " + p);
        String nombreArchivo = "";
        switch (p) {
            case "1-1BSUGWN":
                nombreArchivo = "C:\\pdf\\a.txt";
                break;
            case "1-1BSUGWR":
                nombreArchivo = "C:\\pdf\\b.txt";
                break;
            case "1-1BSUGWP":
                nombreArchivo = "C:\\pdf\\c.txt";
                break;
            default:
        }
        System.out.println("nombreArchivo " + nombreArchivo);
        FileReader fr = null;

        String base64File = generarBase64(nombreArchivo);

        System.out.println(base64File);
        System.out.println("detalleRepController "+detalleRepController);
        byte[] encoded = Base64.decodeBase64(base64File);

//        try {
//            DetalleDocumentoVO detalleDocumentoVO = detalleRepController.detalleRepINOperation(p);
//            encoded=detalleDocumentoVO.getArchivo();
//        } catch (Exception ex) {
//            encoded =Base64.decodeBase64(generarBase64Vacio());
//        }

        File pdfFile = new File("c.pdf");
        FileUtils.writeByteArrayToFile(pdfFile, encoded);

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=a.pdf");
        response.setContentLength((int) pdfFile.length());

        FileInputStream fileInputStream = new FileInputStream(pdfFile);
        OutputStream responseOutputStream = response.getOutputStream();
        int bytes;
        while ((bytes = fileInputStream.read()) != -1) {
            responseOutputStream.write(bytes);
        }
        fileInputStream.close();
        responseOutputStream.close();

    }

    private String generarBase64(String nombreArchivo) {
        if (nombreArchivo.isEmpty()) {
            Properties prop = new Properties();

            try {
                prop.load(getServletContext().getResourceAsStream("/WEB-INF/properties/servlet.properties"));
                System.out.println(prop.getProperty("pdfVacio"));
                return prop.getProperty("pdfVacio");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            FileReader fr = null;
            BufferedReader br = null;
            try {
                fr = new FileReader(nombreArchivo);
                br = new BufferedReader(fr);
                br = new BufferedReader(new FileReader(nombreArchivo));
                return br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(ServletDetalleDocumentoPdf.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (fr != null) {
                        fr.close();
                    }
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ServletDetalleDocumentoPdf.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "";
    }

    private String generarBase64Vacio() {
        Properties prop = new Properties();
        try {
            prop.load(getServletContext().getResourceAsStream("/WEB-INF/properties/servlet.properties"));
            System.out.println(prop.getProperty("pdfVacio"));
            return prop.getProperty("pdfVacio");
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
