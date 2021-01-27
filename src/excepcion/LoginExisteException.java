/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 * Clase que define la excepción de que el login existe.
 *
 * @author Cristina Milea
 */
public class LoginExisteException extends Exception {

    /**
     * Constructor vacío.
     */
    public LoginExisteException() {
        super("El login que se ha buscado ya existe en la base de datos");
    }
}
