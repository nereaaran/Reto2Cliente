/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;

/**
 *
 * @author Jonathan Viñan
 */
public class GrupoLibroId  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer idGrupo;
    private Integer idLibro;
    
     /**
     * Obtinen el idGrupo.
     * @return El ifGrupo.
     */
    public Integer getIdGrupo() {
        return idGrupo;
    }
    /**
     * Establece el idGrupo
     * @param idGrupo
     */
    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }
    /**
     * Obtinene el idLibro
     * @return EL idLibro
     */
    public Integer getIdLibro() {
        return idLibro;
    }
    /**
     * Establece el idLibro
     * @param idLibro
     */
   public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }
       
}
