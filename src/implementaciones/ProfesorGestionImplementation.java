/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import entidad.Profesor;
import interfaces.ProfesorGestion;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import rest.ProfesorRESTClient;
import seguridad.CifradoAsimetrico;

/**
 * Clase que implementa la interfaz ProfesorGestion usando un cliente web
 * RESTful.
 *
 * @author Cristina Milea y Nerea Aranguren
 */
public class ProfesorGestionImplementation implements ProfesorGestion {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("implementaciones.ProfesorGestionImplementation");

    /**
     * Cliente web de REST profesor.
     */
    private final ProfesorRESTClient webClient;

    /**
     * Constructor que sirve para acceder al servicio REST del lado servidor.
     */
    public ProfesorGestionImplementation() {
        webClient = new ProfesorRESTClient();
    }

    /**
     * Manda una petición REST de tipo create al servidor y se crea un nuevo
     * profesor.
     *
     * @param profesor el profesor que se creará.
     */
    @Override
    public void create(Profesor profesor) {
        profesor.setPassword(cifrarContrasena(profesor.getPassword()));

        try {
            LOGGER.info("ProfesorGestionImplementation: Creando profesor");

            webClient.create(profesor);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST de tipo edit al servidor y modifica el profesor.
     *
     * @param profesor el profesor que se modificará.
     */
    @Override
    public void edit(Profesor profesor) {
        profesor.setPassword(cifrarContrasena(profesor.getPassword()));

        try {
            LOGGER.info("ProfesorGestionImplementation: Editando profesor");

            webClient.edit(profesor);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST de tipo remove al servidor y elimina el profesor
     * por el id.
     *
     * @param id el id del profesor que se eliminará.
     */
    @Override
    public void remove(Integer id) {
        try {
            LOGGER.info("ProfesorGestionImplementation: Borrando profesor");

            webClient.remove(id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST de tipo find al servidor y busca el profesor por
     * el id.
     *
     * @param id el id del profesor por el que buscará.
     */
    @Override
    public Profesor find(Integer id) {
        Profesor profesor = null;
        try {
            LOGGER.info("ProfesorGestionImplementation: Buscando profesor por id");

            profesor = webClient.find(Profesor.class, id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }

        return profesor;
    }

    /**
     * Manda una petición REST para que busque todos los profesores al servidor.
     *
     * @return la colección de todos los profesores.
     */
    @Override
    public Collection<Profesor> buscarTodosLosProfesores() {
        Collection<Profesor> profesor = null;

        try {
            LOGGER.info("ProfesorGestionImplementation: Buscando todos los profesores");

            profesor = webClient.buscarTodosLosProfesores(new GenericType<Collection<Profesor>>() {
            });
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }

        return profesor;
    }

    /**
     * Cifra la contraseña con la clave publica.
     *
     * @param contrasena La contraseña del usuario.
     * @return La contraseña cifrada y en hexadecimal.
     */
    private String cifrarContrasena(String contrasena) {
        LOGGER.info("ProfesorGestionImplementation: Cifrando contraseña");
        CifradoAsimetrico cifrar = new CifradoAsimetrico();
        return cifrar.cifrarConClavePublica(contrasena);
    }
}
