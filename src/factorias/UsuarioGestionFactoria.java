/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorias;

import implementaciones.UsuarioGestionImplementation;
import interfaces.UsuarioGestion;
import java.util.logging.Logger;

/**
 * Clase de factoría que gestiona los usuarios.
 *
 * @author Cristina Milea
 */
public class UsuarioGestionFactoria {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("implementaciones.UsuarioGestionFactoria");

    /**
     * Método que crea una nueva implementación para la gestión de usuarios.
     *
     * @return la implementación.
     */
    public static UsuarioGestion crearUsuarioGestion() {
        LOGGER.info("UsuarioGestionFactoria: Creando la implementacion");

        UsuarioGestion usuarioGestion = new UsuarioGestionImplementation();

        return usuarioGestion;
    }
}
