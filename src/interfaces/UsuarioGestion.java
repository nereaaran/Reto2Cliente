/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidad.Alumno;
import entidad.Usuario;
import java.util.Collection;
import javax.ws.rs.ClientErrorException;

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
    public void create(Usuario usuario) throws ClientErrorException;

    /**
     * Método que actualiza la información de un usuario existente.
     *
     * @param usuario Objeto usuario que se va a actualizar.
     */
    public void edit(Usuario usuario) throws ClientErrorException;

    /**
     * Método que elimina un usuario existente.
     *
     * @param id El id del usuario que se va a eliminar.
     */
    public void remove(Integer id) throws ClientErrorException;

    /**
     * Método que obtiene información de un usuario existente por id.
     *
     * @param id El id del usuario del que se quiere obtener la información.
     * @return Objeto Usuario con la informacion del usuario buscado.
     */
    public Usuario find(Integer id) throws ClientErrorException;

    /**
     * Método que busca un usuario por su login.
     *
     * @param login El login del usuario.
     * @return Colección de usuario con el login buscado.
     */
    public Collection<Usuario> buscarUsuarioPorLogin(String login) throws ClientErrorException;

    /**
     * Método que busca un usuario por su email.
     *
     * @param email El email del usuario.
     * @return Colección de usuario con el email buscado.
     */
    public Collection<Usuario> buscarUsuarioPorEmail(String email) throws ClientErrorException;

    /**
     * Método que busca un usuario por su login y contraseña.
     *
     * @param login El login del usuario.
     * @param contrasenia La contraseña del usuario.
     * @return Colección de usuario con el login y contraseña buscada.
     */
    public Collection<Usuario> buscarLoginYContrasenia(String login, String contrasenia) throws ClientErrorException;

    /**
     * Método que busca todos los alumnos.
     *
     * @return Colección de todos los alumnos.
     */
    public Collection<Usuario> consultarTodosAlumnos() throws ClientErrorException;

    /**
     * Método que busca todos los alumnos por nombre.
     *
     * @param nombre El nombre por el que se quire buscar.
     * @return Colección de todos los alumnos con el nombre buscado.
     */
    public Collection<Usuario> buscarAlumnoPorNombre(String nombre) throws ClientErrorException;

}
