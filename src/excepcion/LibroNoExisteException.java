/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 * Clase que define la excepción de que el libro no existe.
 *
 * @author Nerea Aranguren
 */
public class LibroNoExisteException extends Exception {

    /**
     * Constructor vacío.
     */
    public LibroNoExisteException() {
        super("El libro que se ha buscado no existe en la base de datos");
    }
}
