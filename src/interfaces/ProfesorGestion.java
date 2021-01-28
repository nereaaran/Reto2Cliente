/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidad.Profesor;
import java.util.Collection;

/**
 * Interfaz lógica que declara los métodos para la gestión de los profesores.
 *
 * @author Nerea Aranguren
 */
public interface ProfesorGestion {

    /**
     * Método que añade un nuevo profesor creado.
     *
     * @param profesor Objeto profesor que se va a añadir.
     */
    public void create(Profesor profesor);

    /**
     * Método que actualiza la información de un profesor existente.
     *
     * @param profesor Objeto profesor que se va a actualizar.
     */
    public void edit(Profesor profesor);

    /**
     * Método que elimina un profesor existente.
     *
     * @param id El id del profesor que se va a eliminar.
     */
    public void remove(Integer id);

    /**
     * Método que obtiene información de un profesor existente por id.
     *
     * @param id El id del profesor del que se quiere obtener la información.
     * @return Objeto Profesor con la información del profesor. buscado.
     */
    public Profesor find(Integer id);

    /**
     * Método que busca todos los profesores.
     *
     * @return Colección de profesores.
     */
    public Collection<Profesor> buscarTodosLosProfesores();
}
