/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidad.Libro;
import excepcion.LibroNoExisteException;
import java.util.Collection;

/**
 * Interfaz lógica que declara los métodos para la gestión de los libros.
 *
 * @author Nerea Aranguren
 */
public interface LibroGestion {

    /**
     * Método que añade un nuevo libro creado.
     *
     * @param libro Objeto libro que se va a añadir.
     */
    public void create(Libro libro);

    /**
     * Método que actualiza la información de un libro existente.
     *
     * @param libro Objeto libro que se va a actualizar.
     */
    public void edit(Libro libro);

    /**
     * Método que elimina un libro existente.
     *
     * @param libro El objeto Libro que se va a eliminar.
     */
    public void remove(Libro libro);

    /**
     * Método que obtiene información de un libro existente por id.
     *
     * @param id El id del libro del que se quiere obtener la información.
     * @return Objeto Libro con la información del libro buscado.
     */
    public Libro find(Integer id);

    /**
     * Método que busca todos los libros.
     *
     * @return Colección de los libros existentes.
     */
    public Collection<Libro> buscarTodosLosLibros();

    /**
     * Método que busca todos los libros por un titulo.
     *
     * @param titulo El titulo que se quiere buscar.
     * @return Colección de los libros con el titulo buscado.
     * @throws excepcion.LibroNoExisteException Si el libro no existe en la base
     * de datos.
     */
    public Collection<Libro> buscarLibrosPorTitulo(String titulo) throws LibroNoExisteException;

    /**
     * Método que busca todos los libros por un autor.
     *
     * @param autor El autor que se quiere buscar.
     * @return Colección de los libros con el autor buscado.
     */
    public Collection<Libro> buscarLibrosPorAutor(String autor);

}
