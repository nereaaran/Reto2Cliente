/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcion;

/**
 * Clase que define la excepción de que no existen libros.
 *
 * @author Nerea Aranguren
 */
public class LibrosNoExistenException extends Exception {

    /**
     * Constructor vacío.
     */
    public LibrosNoExistenException() {
        super("No existen libros en la base de datos");
    }
}
