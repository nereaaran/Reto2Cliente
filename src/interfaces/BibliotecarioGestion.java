/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidad.Bibliotecario;
import java.util.Collection;

/**
 * Interfaz lógica que declara los métodos para la gestión de los
 * bibliotecarios.
 *
 * @author Nerea Aranguren
 */
public interface BibliotecarioGestion {

    /**
     * Método que añade un nuevo bibliotecario creado.
     *
     * @param bibliotecario Objeto bibliotecario que se va a añadir.
     */
    public void create(Bibliotecario bibliotecario);

    /**
     * Método que actualiza la información de un bibliotecario existente.
     *
     * @param bibliotecario Objeto bibliotecario que se va a actualizar.
     */
    public void edit(Bibliotecario bibliotecario);

    /**
     * Método que elimina un bibliotecario existente.
     *
     * @param id El id del bibliotecario que se va a eliminar.
     */
    public void remove(Integer id);

    /**
     * Método que obtiene información de un bibliotecario existente por id.
     *
     * @param id El id del bibliotecario del que se quiere obtener la
     * información.
     * @return Objeto Bibliotecario con la informacion del bibliotecario
     * buscado.
     */
    public Bibliotecario find(Integer id);

    /**
     * Método que busca todos los bibliotecarios.
     *
     * @return Colección de bibliotecarios.
     */
    public Collection<Bibliotecario> buscarTodosLosBibliotecarios();
}
