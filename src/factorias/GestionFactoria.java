/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorias;

import implementaciones.*;
import interfaces.*;
import java.util.logging.Logger;

/**
 * Clase de factoría que gestiona las implementaciones.
 *
 * @author Cristina Milea
 */
public class GestionFactoria {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("implementaciones.GestionFactoria");

    /**
     * Método que crea una nueva implementación para la gestión de usuarios.
     *
     * @return la implementación.
     */
    public static UsuarioGestion getUsuarioGestion() {
        LOGGER.info("GestionFactoria: Creando la implementacion de Usuario");

        UsuarioGestion usuarioGestion = new UsuarioGestionImplementation();

        return usuarioGestion;
    }

    /**
     * Método que crea una nueva implementación para la gestión de profesores.
     *
     * @return la implementación.
     */
    public static ProfesorGestion getProfesorGestion() {
        LOGGER.info("GestionFactoria: Creando la implementacion de Profesor");

        ProfesorGestion profesorGestion = new ProfesorGestionImplementation();

        return profesorGestion;
    }

    /**
     * Método que crea una nueva implementación para la gestión de alumnos.
     *
     * @return la implementación.
     */
    public static AlumnoGestion getAlumnoGestion() {
        LOGGER.info("GestionFactoria: Creando la implementacion de Alumno");

        AlumnoGestion alumnoGestion = new AlumnoGestionImplementation();

        return alumnoGestion;
    }

    /**
     * Método que crea una nueva implementación para la gestión de libros.
     *
     * @return la implementación.
     */
    public static LibroGestion getLibroGestion() {
        LOGGER.info("GestionFactoria: Creando la implementacion de Libro");

        LibroGestion libroGestion = new LibroGestionImplementation();

        return libroGestion;
    }

    /**
     * Método que crea una nueva implementación para la gestión de grupos.
     *
     * @return la implementación.
     */
    public static GrupoGestion getGrupoGestion() {
        LOGGER.info("GestionFactoria: Creando la implementacion de Grupo");

        GrupoGestion grupoGestion = new GrupoGestionImplementation();

        return grupoGestion;
    }

    /**
     * Método que crea una nueva implementación para la gestión de
     * bibliotecarios.
     *
     * @return la implementación.
     */
    public static BibliotecarioGestion getBibliotecarioGestion() {
        LOGGER.info("GestionFactoria: Creando la implementacion de Bibliotecario");

        BibliotecarioGestion bibliotecarioGestion = new BibliotecarioGestionImplementation();

        return bibliotecarioGestion;
    }
}
