/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidad.Grupo;
import java.util.Collection;
import javax.ws.rs.ClientErrorException;

/**
 * Interfaz lógica que declara los métodos para la gestión de los grupos.
 *
 * @author Nerea Aranguren
 */
public interface GrupoGestion {

    /**
     * Método que añade un nuevo grupo creado.
     *
     * @param grupo Objeto grupo que se va a añadir.
     */
    public void create(Grupo grupo) throws ClientErrorException;

    /**
     * Método que actualiza la información de un grupo existente.
     *
     * @param grupo Objeto grupo que se va a actualizar.
     */
    public void edit(Grupo grupo) throws ClientErrorException;

    /**
     * Método que elimina un grupo existente.
     *
     * @param id El id del grupo que se va a eliminar.
     */
    public void remove(Integer id) throws ClientErrorException;

    /**
     * Método que obtiene información de un grupo existente por id.
     *
     * @param id El id del grupo del que se quiere obtener la información.
     * @return Objeto grupo con la información del grupo buscado.
     */
    public Grupo find(Integer id) throws ClientErrorException;

    /**
     * Método que obtiene información de un grupo existente por el nombre.
     *
     * @param nombre El nombre del grupo del que se quiere obtener la
     * información.
     * @return Objeto grupo con la información del grupo buscado.
     */
    public Collection<Grupo> buscarGrupoPorNombre(String nombre) throws ClientErrorException;

    /**
     * Método que obtiene todos los grupos existentes.
     *
     * @return Colección de todos los grupos.
     */
    public Collection<Grupo> buscarTodosLosGruposGrupo() throws ClientErrorException;

}
