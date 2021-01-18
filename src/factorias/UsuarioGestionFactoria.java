/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorias;

import implementaciones.UsuarioGestionImplementacion;
import interfaces.UsuarioGestion;

/**
 * Clase de factoría que gestiona los usuarios.
 *
 * @author Cristina Milea
 */
public class UsuarioGestionFactoria {

    /**
     * Método que crea una nueva implementación para la gestión de usuarios.
     *
     * @return la implementación.
     */
    public static UsuarioGestion crearUsuarioGestion() {
        UsuarioGestion usuarioGestion = new UsuarioGestionImplementacion();

        return usuarioGestion;
    }
}
