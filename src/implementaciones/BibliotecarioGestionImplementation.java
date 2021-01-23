/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import entidad.Bibliotecario;
import interfaces.BibliotecarioGestion;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import rest.BibliotecarioRESTClient;
import seguridad.CifradoAsimetrico;

/**
 * Clase que implementa la interfaz BibliotecarioGestion usando un cliente web
 * RESTful.
 *
 * @author Cristina Milea
 */
public class BibliotecarioGestionImplementation implements BibliotecarioGestion {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("implementaciones.BibliotecarioGestionImplementation");

    /**
     * Cliente web de REST bibliotecario.
     */
    private final BibliotecarioRESTClient webClient;

    /**
     * Constructor que sirve para acceder al servicio REST del lado servidor.
     */
    public BibliotecarioGestionImplementation() {
        webClient = new BibliotecarioRESTClient();
    }

    /**
     * Manda una petición REST de tipo create al servidor y se crea un nuevo
     * bibliotecario.
     *
     * @param bibliotecario el bibliotecario que se creará.
     */
    @Override
    public void create(Bibliotecario bibliotecario) {
        bibliotecario.setPassword(cifrarContrasena(bibliotecario.getPassword()));
        
        try {
            LOGGER.info("BibliotecarioGestionImplementation: Creando bibliotecario");

            webClient.create(bibliotecario);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST de tipo edit al servidor y modifica el
     * bibliotecario.
     *
     * @param bibliotecario el bibliotecario que se modificará.
     */
    @Override
    public void edit(Bibliotecario bibliotecario) {
        bibliotecario.setPassword(cifrarContrasena(bibliotecario.getPassword()));
        
        try {
            LOGGER.info("BibliotecarioGestionImplementation: Editando bibliotecario");

            webClient.edit(bibliotecario);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST de tipo remove al servidor y elimina el
     * bibliotecario por el id.
     *
     * @param id el id del bibliotecario que se eliminará.
     */
    @Override
    public void remove(Integer id) {
        try {
            LOGGER.info("BibliotecarioGestionImplementation: Borrando bibliotecario");

            webClient.remove(id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST de tipo find al servidor y busca el bibliotecario
     * por el id.
     *
     * @param id el id del bibliotecario por el que buscará.
     */
    @Override
    public Bibliotecario find(Integer id) {
        Bibliotecario bibliotecario = null;
        try {
            LOGGER.info("BibliotecarioGestionImplementation: Buscando bibliotecario por id");

            bibliotecario = webClient.find(Bibliotecario.class, id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }

        return bibliotecario;
    }

    /**
     * Manda una petición REST para que busque todos los bibliotecarios al
     * servidor.
     *
     * @return la colección de todos los bibliotecarios.
     */
    @Override
    public Collection<Bibliotecario> buscarTodosLosBibliotecarios() {
        Collection<Bibliotecario> bibliotecario = null;

        try {
            LOGGER.info("BibliotecarioGestionImplementation: Buscando todos los bibliotecarios");

            bibliotecario = webClient.buscarTodosLosBibliotecarios(new GenericType<Collection<Bibliotecario>>() {
            });
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }

        return bibliotecario;
    }

    /**
     * Cifra la contraseña con la clave publica.
     *
     * @param contrasena La contraseña del usuario.
     * @return La contraseña cifrada y en hexadecimal.
     */
    private String cifrarContrasena(String contrasena) {
        LOGGER.info("BibliotecarioGestionImplementation: Cifrando contraseña");
        CifradoAsimetrico cifrar = new CifradoAsimetrico();
        return cifrar.cifrarConClavePublica(contrasena);
    }
}
