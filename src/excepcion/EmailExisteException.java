/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 * Clase que define la excepción de que el email existe.
 *
 * @author Cristina Milea
 */
public class EmailExisteException extends Exception {

    /**
     * Constructor vacío.
     */
    public EmailExisteException() {
        super("El email que se ha buscado ya existe en la base de datos");
    }
}
