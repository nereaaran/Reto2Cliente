/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import entidad.Usuario;
import interfaces.UsuarioGestion;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import rest.UsuarioRESTClient;

/**
 * Clase que implementa la interfaz UsuarioGestion usando un cliente web
 * RESTful.
 *
 * @author Cristina Milea
 */
public class UsuarioGestionImplementation implements UsuarioGestion {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("implementaciones.UsuarioGestionImplementation");

    /**
     * Cliente web de REST usuario.
     */
    private final UsuarioRESTClient webClient;

    /**
     * Constructor que sirve para acceder al servicio REST del lado servidor.
     */
    public UsuarioGestionImplementation() {
        webClient = new UsuarioRESTClient();
    }

    /**
     * Manda una petición REST de tipo create al servidor y se crea un nuevo
     * usuario.
     *
     * @param usuario el usuario que se creará.
     */
    @Override
    public void create(Usuario usuario) {
        try {
            LOGGER.info("UsuarioGestionImplementation: Creando usuario");

            webClient.create(usuario);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST de tipo edit al servidor y modifica el usuario.
     *
     * @param usuario el usuario que se modificará.
     */
    @Override
    public void edit(Usuario usuario) {
        try {
            LOGGER.info("UsuarioGestionImplementation: Editando usuario");

            webClient.edit(usuario);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST de tipo remove al servidor y elimina el usuario
     * por el id.
     *
     * @param id el id del usuario que se eliminará.
     */
    @Override
    public void remove(Integer id) {
        try {
            LOGGER.info("UsuarioGestionImplementation: Borrando usuario");

            webClient.remove(id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST de tipo find al servidor y busca el usuario por
     * el id.
     *
     * @param id el id del usuario por el que buscará.
     */
    @Override
    public Usuario find(Integer id) {
        Usuario usuario = null;
        try {
            LOGGER.info("UsuarioGestionImplementation: Buscando usuario por id");

            usuario = webClient.find(Usuario.class, id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }

        return usuario;
    }

    /**
     * Manda una petición REST para que busque un usuario por login al servidor
     * y busca el usuario.
     *
     * @param login el login del usuario por el que se buscará.
     * @return la colección de usuarios que encuentra.
     */
    @Override
    public Collection<Usuario> buscarUsuarioPorLogin(String login) {
        Collection<Usuario> usuario = null;

        try {
            LOGGER.info("UsuarioGestionImplementation: Buscando usuario por login");

            usuario = webClient.buscarUsuarioPorLogin(new GenericType<Collection<Usuario>>() {
            }, login);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }

        return usuario;
    }

    /**
     * Manda una petición REST para que busque un usuario por email al servidor
     * y busca el usuario.
     *
     * @param email el email del usuario por el que se buscará.
     * @return la colección de usuarios que encuentra.
     */
    @Override
    public Collection<Usuario> buscarUsuarioPorEmail(String email) {
        Collection<Usuario> usuario = null;

        try {
            LOGGER.info("UsuarioGestionImplementation: Buscando usuario por email");

            usuario = webClient.buscarUsuarioPorEmail(new GenericType<Collection<Usuario>>() {
            }, email);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }

        return usuario;
    }

    /**
     * Manda una petición REST para que busque un usuario por login y contraseña
     * al servidor y busca el usuario.
     *
     * @param login el login del usuario por el que se buscará.
     * @param contrasenia la contraseña del usuario por el que se buscará.
     * @return la colección de usuarios que encuentra.
     */
    @Override
    public Collection<Usuario> buscarLoginYContrasenia(String login, String contrasenia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Manda una petición REST para que busque todos los usuarios de tipo alumno
     * al servidor y busca el usuario.
     *
     * @return la colección de usuarios que encuentra.
     */
    @Override
    public Collection<Usuario> consultarTodosAlumnos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Manda una petición REST para que busque un usuario de tipo alumno por el
     * nombre al servidor y busca el usuario.
     *
     * @param nombre el nombre del alumno.
     * @return la colección de usuarios que encuentra.
     */
    @Override
    public Collection<Usuario> buscarAlumnoPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
