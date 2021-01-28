/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import entidad.Libro;
import interfaces.LibroGestion;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import rest.LibroRESTClient;

/**
 * Clase que implementa la interfaz LibroGestion usando un cliente web RESTful.
 *
 * @author Nerea Aranguren
 */
public class LibroGestionImplementation implements LibroGestion {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("implementaciones.LibroGestionImplementation");

    /**
     * Cliente web de REST libro.
     */
    private LibroRESTClient webClient;

    /**
     * Crea un objeto LibroGestionImplementation. Construye un cliente web para
     * acceder al servicio REST del lado servidor.
     */
    public LibroGestionImplementation() {
        webClient = new LibroRESTClient();
    }

    /**
     * Añade un nuevo libro creado mandando una peticion POST al servicio web
     * RESTful.
     *
     * @param libro El objeto Libro que se añadirá.
     * @throws ClientErrorException Si hay algun error durante el proceso.
     */
    @Override
    public void create(Libro libro) {
        try {
            LOGGER.info("LibroGestionImplementation: Creando libro");

            webClient.create(libro);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Modifica un libro mandando una peticion PUT al servicio web RESTful.
     *
     * @param libro El objeto Libro que se modificará.
     * @throws ClientErrorException Si hay algun error durante el proceso.
     */
    @Override
    public void edit(Libro libro) {
        try {
            LOGGER.info("LibroGestionImplementation: Editando libro");

            this.webClient.edit(libro);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Elimina un libro mandando una peticion DELETE al servicio web RESTful.
     *
     * @param libro El objeto Libro que se eliminará.
     * @throws ClientErrorException Si hay algun error durante el proceso.
     */
    @Override
    public void remove(Libro libro) {
        try {
            LOGGER.info("LibroGestionImplementation: Eliminando libro");

            this.webClient.remove(libro.getIdLibro());
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Obtiene un libro mandando una peticion GET al servicio web RESTful.
     *
     * @param id El id del objeto libro que se buscará.
     * @return Un objeto Libro con los datos.
     * @throws ClientErrorException Si hay algun error durante el proceso.
     */
    @Override
    public Libro find(Integer id) {
        Libro libro = null;
        try {
            LOGGER.info("LibroGestionImplementation: Buscando libro por id");

            libro = this.webClient.find(Libro.class, id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
        return libro;
    }

    /**
     * Obtiene todos los libros mandando una petición GET al servicio web
     * RESTful.
     *
     * @return Una colección de objetos Libro con los datos.
     * @throws ClientErrorException Si hay algun error durante el proceso.
     */
    @Override
    public Collection<Libro> buscarTodosLosLibros() {
        Collection<Libro> libros = null;
        try {
            LOGGER.info("LibroGestionImplementation: Buscando todos los libros");

            libros = this.webClient.buscarTodosLosLibros(new GenericType<Collection<Libro>>() {
            });
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
        return libros;
    }

    /**
     * Obtiene un libro mandando una peticion GET al servicio web RESTful.
     *
     * @param titulo El titulo del objeto libro que se buscará.
     * @return Una colección de objetos Libro con los datos.
     * @throws ClientErrorException Si hay algun error durante el proceso.
     */
    @Override
    public Collection<Libro> buscarLibrosPorTitulo(String titulo) {
        Collection<Libro> libros = null;
        try {
            LOGGER.info("LibroGestionImplementation: Buscando libros por titulo");

            libros = this.webClient.buscarLibrosPorTitulo(new GenericType<Collection<Libro>>() {
            }, titulo);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
        return libros;
    }

    /**
     * Obtiene un libro mandando una peticion GET al servicio web RESTful.
     *
     * @param autor El autor del objeto libro que se buscará.
     * @return Una colección de objetos Libro con los datos.
     * @throws ClientErrorException Si hay algun error durante el proceso.
     */
    @Override
    public Collection<Libro> buscarLibrosPorAutor(String autor) {
        Collection<Libro> libros = null;
        try {
            LOGGER.info("LibroGestionImplementation: Buscando libros por autor");

            libros = this.webClient.buscarLibrosPorTitulo(new GenericType<Collection<Libro>>() {
            }, autor);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
        return libros;
    }
}
