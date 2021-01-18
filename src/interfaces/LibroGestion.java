/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidad.Libro;
import java.util.Collection;
import javax.ws.rs.ClientErrorException;

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
    public void create(Libro libro) throws ClientErrorException;

    /**
     * Método que actualiza la información de un libro existente.
     *
     * @param libro Objeto libro que se va a actualizar.
     */
    public void edit(Libro libro) throws ClientErrorException;

    /**
     * Método que elimina un libro existente.
     *
     * @param id El id del libro que se va a eliminar.
     */
    public void remove(Integer id) throws ClientErrorException;

    /**
     * Método que obtiene información de un libro existente por id.
     *
     * @param id El id del libro del que se quiere obtener la información.
     * @return Objeto Libro con la información del libro buscado.
     */
    public Libro find(Integer id) throws ClientErrorException;

    /**
     * Método que busca todos los libros.
     *
     * @return Colección de los libros existentes.
     */
    public Collection<Libro> buscarTodosLosLibros() throws ClientErrorException;

    /**
     * Método que busca todos los libros por un titulo.
     *
     * @param titulo El titulo que se quiere buscar.
     * @return Colección de los libros con el titulo buscado.
     */
    public Collection<Libro> buscarLibrosPorTitulo(String titulo) throws ClientErrorException;

    /**
     * Método que busca todos los libros por un autor.
     *
     * @param autor El autor que se quiere buscar.
     * @return Colección de los libros con el autor buscado.
     */
    public Collection<Libro> buscarLibrosPorAutor(String autor) throws ClientErrorException;

}
