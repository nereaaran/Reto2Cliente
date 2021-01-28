/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import excepcion.*;
import entidad.Usuario;
import interfaces.UsuarioGestion;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import rest.UsuarioRESTClient;
import seguridad.CifradoAsimetrico;

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
        usuario.setPassword(cifrarContrasena(usuario.getPassword()));
        
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
        usuario.setPassword(cifrarContrasena(usuario.getPassword()));
        
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
     * @throws excepcion.LoginExisteException si el usuario ya existe en la base
     * de datos.
     */
    @Override
    public Collection<Usuario> buscarUsuarioPorLoginCrear(String login) throws LoginExisteException {
        Collection<Usuario> usuario = null;

        try {
            LOGGER.info("UsuarioGestionImplementation: Buscando usuario por login");

            usuario = webClient.buscarUsuarioPorLogin(new GenericType<Collection<Usuario>>() {
            }, login);

            if (!usuario.isEmpty()) {
                throw new LoginExisteException();
            }
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
     * @throws excepcion.LoginNoExisteException si el usuario no existe en la
     * base de datos.
     */
    @Override
    public Collection<Usuario> buscarUsuarioPorLoginSignIn(String login) throws LoginNoExisteException {
        Collection<Usuario> usuario = null;

        try {
            LOGGER.info("UsuarioGestionImplementation: Buscando usuario por login");

            usuario = webClient.buscarUsuarioPorLogin(new GenericType<Collection<Usuario>>() {
            }, login);

            if (usuario.isEmpty()) {
                throw new LoginNoExisteException();
            }
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
     * @throws excepcion.EmailExisteException si el email existe en la base de
     * datos.
     */
    @Override
    public Collection<Usuario> buscarUsuarioPorEmailCrear(String email) throws EmailExisteException {
        Collection<Usuario> usuario = null;

        try {
            LOGGER.info("UsuarioGestionImplementation: Buscando usuario por email");

            usuario = webClient.buscarUsuarioPorEmail(new GenericType<Collection<Usuario>>() {
            }, email);

            if (!usuario.isEmpty()) {
                throw new EmailExisteException();
            }
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
     * @throws excepcion.EmailNoExisteException si el email no existe en la base
     * de datos.
     */
    @Override
    public Collection<Usuario> buscarUsuarioPorEmailContrasenia(String email) throws EmailNoExisteException {
        Collection<Usuario> usuario = null;

        try {
            LOGGER.info("UsuarioGestionImplementation: Buscando usuario por email");

            usuario = webClient.buscarUsuarioPorEmail(new GenericType<Collection<Usuario>>() {
            }, email);

            if (usuario.isEmpty()) {
                throw new EmailNoExisteException();
            }
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }

        return usuario;
    }

    /**
     * Manda una petición REST para que busque un usuario por email al servidor
     * para enviar el mail de recuperación de contraseña.
     *
     * @param usuario el usuario que se buscará.
     */
    @Override
    public void buscarUsuarioParaEnviarMailRecuperarContrasenia(Usuario usuario) {
        try {
            LOGGER.info("UsuarioGestionImplementation: Buscando usuario por email para enviar mail de recuperación de contraseña");

            webClient.buscarUsuarioParaEnviarMailRecuperarContrasenia(usuario);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }
    
    /**
     * Manda una petición REST para que busque un usuario por email al servidor
     * para enviar el mail de cambio de contraseña.
     *
     * @param email el email que se buscará.
     */
    @Override
    public void buscarEmailParaEnviarMailCambiarContrasenia(String email) {
        try {
            LOGGER.info("UsuarioGestionImplementation: Buscando email para enviar mail de cambio de contraseña");

            webClient.buscarEmailParaEnviarMailCambiarContrasenia(email);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST para que busque un usuario por login y contraseña
     * al servidor y busca el usuario.
     *
     * @param login el login del usuario por el que se buscará.
     * @param contrasenia la contraseña del usuario por el que se buscará.
     * @return la colección de usuarios que encuentra.
     * @throws excepcion.UsuarioNoExisteException si el usuario no existe en la
     * base de datos.
     */
    @Override
    public Collection<Usuario> buscarUsuarioPorLoginYContrasenia(String login, String contrasenia) throws UsuarioNoExisteException {
        Collection<Usuario> usuario = null;
        contrasenia = cifrarContrasena(contrasenia);

        try {
            LOGGER.info("UsuarioGestionImplementation: Buscando usuario por login y contrasenia");

            usuario = webClient.buscarUsuarioPorLoginYContrasenia(new GenericType<Collection<Usuario>>() {
            }, login, contrasenia);

            if (usuario.isEmpty()) {
                throw new UsuarioNoExisteException();
            }
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }

        return usuario;
    }

    /**
     * Manda una petición REST para que busque todos los usuarios al servidor.
     *
     * @return la colección de todos los usuarios.
     */
    @Override
    public Collection<Usuario> buscarTodosLosUsuarios() {
        Collection<Usuario> usuario = null;

        try {
            LOGGER.info("UsuarioGestionImplementation: Buscando todos los usuarios");

            usuario = webClient.buscarTodosLosUsuarios(new GenericType<Collection<Usuario>>() {
            });
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }

        return usuario;
    }

    /**
     * Cifra la contraseña con la clave publica.
     *
     * @param contrasena La contraseña del usuario.
     * @return La contraseña cifrada y en hexadecimal.
     */
    private String cifrarContrasena(String contrasena) {
        LOGGER.info("UsuarioGestionImplementation: Cifrando contraseña");
        CifradoAsimetrico cifrar = new CifradoAsimetrico();
        return cifrar.cifrarConClavePublica(contrasena);
    }
}
