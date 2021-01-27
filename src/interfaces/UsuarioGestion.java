/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidad.Alumno;
import entidad.Usuario;
import excepcion.*;
import java.util.Collection;

/**
 * Interfaz lógica que declara los métodos para la gestión de los usuarios.
 *
 * @author Nerea Aranguren
 */
public interface UsuarioGestion {

    /**
     * Método que añade un nuevo usuario creado.
     *
     * @param usuario Objeto usuario que se va a añadir.
     */
    public void create(Usuario usuario);

    /**
     * Método que actualiza la información de un usuario existente.
     *
     * @param usuario Objeto usuario que se va a actualizar.
     */
    public void edit(Usuario usuario);

    /**
     * Método que elimina un usuario existente.
     *
     * @param id El id del usuario que se va a eliminar.
     */
    public void remove(Integer id);

    /**
     * Método que obtiene información de un usuario existente por id.
     *
     * @param id El id del usuario del que se quiere obtener la información.
     * @return Objeto Usuario con la informacion del usuario buscado.
     */
    public Usuario find(Integer id);

    /**
     * Método que busca un usuario por su login.
     *
     * @param login El login del usuario.
     * @return Colección de usuario con el login buscado.
     * @throws excepcion.LoginExisteException si el usuario ya existe en la base
     * de datos.
     */
    public Collection<Usuario> buscarUsuarioPorLoginCrear(String login) throws LoginExisteException;
    
    /**
     * Método que busca un usuario por su login.
     *
     * @param login El login del usuario.
     * @return Colección de usuario con el login buscado.
     * @throws excepcion.LoginNoExisteException si el usuario no existe en la
     * base de datos.
     */
    public Collection<Usuario> buscarUsuarioPorLoginSignIn(String login) throws LoginNoExisteException;

    /**
     * Método que busca un usuario por su email.
     *
     * @param email El email del usuario.
     * @return Colección de usuario con el email buscado.
     * @throws excepcion.EmailExisteException si el email ya existe en la base
     * de datos.
     */
    public Collection<Usuario> buscarUsuarioPorEmailCrear(String email) throws EmailExisteException;

    /**
     * Método que busca un usuario por su email.
     *
     * @param email El email del usuario.
     * @throws excepcion.EmailNoExisteException si el email no existe en la base
     * de datos.
     */
    public Collection<Usuario> buscarUsuarioPorEmailContra(String email) throws EmailNoExisteException;
    
    /**
     * Método que busca un usuario por su email.
     *
     * @param usuario la entidad Usuario.
     * @return Colección de usuario con el email buscado.
     */
    public void buscarEmailParaEnviarMailContraseniaOlvidada(Usuario usuario);
    
    /**
     * Método que busca un usuario por su login y contraseña.
     *
     * @param login El login del usuario.
     * @param contrasenia La contraseña del usuario.
     * @return Colección de usuarios con el login y contraseña buscada.
     * @throws excepcion.UsuarioNoExisteException si el usuario no existe en la
     * base de datos.
     */
    public Collection<Usuario> buscarUsuarioPorLoginYContrasenia(String login, String contrasenia) throws UsuarioNoExisteException;

    /**
     * Método que busca todos los usuarios.
     *
     * @return Colección de usuarios.
     */
    public Collection<Usuario> buscarTodosLosUsuarios();
}
