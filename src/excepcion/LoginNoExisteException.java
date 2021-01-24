/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 * Clase que define la excepción de que el login no existe.
 *
 * @author Cristina Milea
 */
public class LoginNoExisteException extends Exception {

    /**
     * Constructor vacío.
     */
    public LoginNoExisteException() {
        super("El login que se ha buscado no existe en la base de datos");
    }
}
