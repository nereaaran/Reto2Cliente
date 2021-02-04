/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan Viñan
 */
@XmlRootElement(name = "grupo_libro")
public class GrupoLibro implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id f de la clase GrupoLibro.
     */
    private GrupoLibroId idGrupoLibro;
    /**
     * El libro perteneciente a la relacion de grupoLibro.
     */
    private Libro libro;

    /**
     * Fecha en la que se asigna el libro.
     */
    private Date fechaInicio;

    /**
     * Fecha maxima asignada para el libro.
     */
    private Date fechaFin;

    /**
     * Estable el GrupoLibro
     *
     * @return el grupoLibro
     */
    public GrupoLibroId getIdGrupoLibro() {
        return idGrupoLibro;
    }

    /**
     * Obtine el GrupoLibro
     *
     * @param grupoLibro el grupoLibro.
     */
    public void setIdGrupoLibro(GrupoLibroId idGrupoLibro) {
        this.idGrupoLibro = idGrupoLibro;
    }

    /**
     * Estable el Libro
     *
     * @return el libro
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Obtine el libro
     *
     * @param libro el libro.
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Establece la fecha de inicio
     *
     * @return la fecha inicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Obtiene la fecha inicio
     *
     * @param fechaInicio la fecha de inicio.
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Establece la fecha final
     *
     * @return la fecha final
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * Obtiene la fecha final
     *
     * @param fechaFin la fecha de fin.
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Metodo que compara el codigo hash de dos objetos
     *
     * @return un codigo hash del objeto
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.libro);
        hash = 31 * hash + Objects.hashCode(this.fechaInicio);
        hash = 31 * hash + Objects.hashCode(this.fechaFin);
        return hash;
    }

    /**
     * Metodo que comprara si un objeto es igual al objeto GrupoLibro
     *
     * @param obj cualquier tipo de objeto
     * @return un 'false' si los objetos no son iguales y un 'true' si lo sons
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
        final GrupoLibro other = (GrupoLibro) obj;
        if (!Objects.equals(this.libro, other.libro)) {
            return false;
        }
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        return true;
    }
}
