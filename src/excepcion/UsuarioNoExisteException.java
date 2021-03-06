/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 * Clase que define la excepción de que el usuario no existe.
 *
 * @author Cristina Milea
 */
public class UsuarioNoExisteException extends Exception {

    /**
     * Constructor vacío.
     */
    public UsuarioNoExisteException() {
        super("El usuario que se ha buscado no existe en la base de datos");
    }
}
