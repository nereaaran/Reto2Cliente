/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidad.Alumno;
import java.util.Collection;

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
    public void create(Alumno alumno);

    /**
     * Método que actualiza la información de un alumno existente.
     *
     * @param alumno Objeto bibliotecario que se va a actualizar.
     */
    public void edit(Alumno alumno);

    /**
     * Método que elimina un alumno existente.
     *
     * @param id El id del alumno que se va a eliminar.
     */
    public void remove(Integer id);

    /**
     * Método que obtiene información de un alumno existente por id.
     *
     * @param id El id del alumno del que se quiere obtener la información.
     * @return Objeto Alumno con la información del alumno. buscado.
     */
    public Alumno find(Integer id);

    /**
     * Método que busca un alumno por su nombre.
     *
     * @param fullName El nombre del alumno.
     * @return Colección de alumnos con el nombre buscado.
     */
    public Collection<Alumno> buscarAlumnoPorNombre(String fullName);

    /**
     * Método que busca todos los alumnos.
     *
     * @return Colección de alumnos.
     */
    public Collection<Alumno> buscarTodosLosAlumnos();
}
