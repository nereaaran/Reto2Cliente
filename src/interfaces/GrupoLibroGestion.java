/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidad.GrupoLibro;
import java.util.Collection;

/**
 *
 * @author JonY
 */
public interface GrupoLibroGestion {
    /**
     * Método que añade un nuevo grupo creado.
     *
     * @param grupo Objeto grupo que se va a añadir.
     */
    public void create(GrupoLibro grupo)  ;

    /**
     * Método que actualiza la información de un grupo existente.
     *
     * @param grupo Objeto grupo que se va a actualizar.
     */
    public void edit(GrupoLibro grupo)  ;

    /**
     * Método que elimina un grupo existente.
     *
     * @param id El id del grupo que se va a eliminar.
     */
    public void remove(Integer id)  ;

    /**
     * Método que obtiene información de un grupo existente por id.
     *
     * @param id El id del grupo del que se quiere obtener la información.
     * @return Objeto grupo con la información del grupo buscado.
     */
    public GrupoLibro find(Integer id)  ;


}
