/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Jonathan Viñan
 */
public class GrupoLibroId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idGrupo;
    private Integer idLibro;

    public GrupoLibroId() {
    }

    public GrupoLibroId(Integer idGrupo, Integer idLibro) {
        this.idGrupo = idGrupo;
        this.idLibro = idLibro;
    }

    /**
     * Obtinen el idGrupo.
     *
     * @return El ifGrupo.
     */
    public Integer getIdGrupo() {
        return idGrupo;
    }

    /**
     * Establece el idGrupo
     *
     * @param idGrupo el id del grupo
     */
    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    /**
     * Obtinene el idLibro
     *
     * @return EL idLibro
     */
    public Integer getIdLibro() {
        return idLibro;
    }

    /**
     * Establece el idLibro
     *
     * @param idLibro el id del libro.
     */
    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    /**
     * Método que compara el código hash de dos objetos.
     *
     * @return el código hash del objeto.
     */
   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idGrupo);
        hash = 89 * hash + Objects.hashCode(this.idLibro);
        return hash;
    }

    
    /**
     * Método que compara si un objeto es igual al objeto "GrupoLibroId".
     *
     * @param obj cualquier tipo de objeto.
     * @return un "false" si los objetos noson iguales y un "true" si lo son.
     */
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GrupoLibroId other = (GrupoLibroId) obj;
        if (!Objects.equals(this.idGrupo, other.idGrupo)) {
            return false;
        }
        if (!Objects.equals(this.idLibro, other.idLibro)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GrupoLibroId{" + "idGrupo=" + idGrupo + ", idLibro=" + idLibro + '}';
    }

    
}
