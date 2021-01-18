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
    public void create(Libro libro) throws ClientErrorException {
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
    public void edit(Libro libro) throws ClientErrorException {
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
    public void remove(Libro libro) throws ClientErrorException {
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
    public Libro find(Integer id) throws ClientErrorException {
        Libro libro = null;
        try {
            LOGGER.info("LibroGestionImplementation: Buscando libro por id");

            libro = this.webClient.find(Libro.class, id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
        return libro;
    }

    @Override
    public Collection<Libro> buscarTodosLosLibros() throws ClientErrorException {
    Collection<Libro> libros = null;
        try {
            LOGGER.info("LibroGestionImplementation: Buscando todos los libros");

            libros = this.webClient.buscarTodosLosLibros(Libro.class);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
        return libros;
    }

    @Override
    public Collection<Libro> buscarLibrosPorTitulo(String titulo) throws ClientErrorException {
    Collection<Libro> libros = null;
        try {
            LOGGER.info("LibroGestionImplementation: Buscando libros por titulo");

            libros = this.webClient.buscarLibrosPorTitulo(Libro.class, titulo);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
        return libros;
    }

    @Override
    public Collection<Libro> buscarLibrosPorAutor(String autor) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
