/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidad.Alumno;
import javax.ws.rs.ClientErrorException;

/**
 * Interfaz lógica que declara los métodos para la gestión de los alumnos.
 *
 * @author Nerea Aranguren
 */
public interface AlumnoGestion {

    /**
     * Método que añade un nuevo alumno creado.
     *
     * @param alumno Objeto alumno que se va a añadir.
     */
    public void create(Alumno alumno) throws ClientErrorException;

    /**
     * Método que actualiza la información de un alumno existente.
     *
     * @param alumno Objeto bibliotecario que se va a actualizar.
     */
    public void edit(Alumno alumno) throws ClientErrorException;

    /**
     * Método que elimina un alumno existente.
     *
     * @param id El id del alumno que se va a eliminar.
     */
    public void remove(Integer id) throws ClientErrorException;

    /**
     * Método que obtiene información de un alumno existente por id.
     *
     * @param id El id del alumno del que se quiere obtener la información.
     * @return Objeto Alumno con la información del alumno. buscado.
     */
    public Alumno find(Integer id) throws ClientErrorException;
}
