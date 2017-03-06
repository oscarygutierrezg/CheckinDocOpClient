/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.cla.web.firma.exception;

/**
 *
 * @author oscar
 */
public class FirmaException extends Exception{

    public FirmaException() {
    }
    
    public FirmaException(String message) {
        super(message);
    }
    
    public FirmaException(Throwable cause) {
        super(cause);
    }

    public FirmaException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    
}
