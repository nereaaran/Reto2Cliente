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
 * Clase para guardar las relaciones entre alumno y libro y sus atributos.
 *
 * @author Nerea Aranguren
 */
@XmlRootElement(name = "alumno_libro")
public class AlumnoLibro implements Serializable {

    private static final long serialVersionUID = 1L;

    private AlumnoLibroId idAlumnoLibro;
    private Libro libro;

    private Date fechaAsignado;
    private Date fechaLimite;

    /**
     * Obtiene el idAlumnoLibro de AlumnoLibro.
     *
     * @return El idAlumnoLibro de AlumnoLibro.
     */
    public AlumnoLibroId getIdAlumnoLibro() {
        return idAlumnoLibro;
    }

    /**
     * Establece el idAlumnoLibro de AlumnoLibro.
     *
     * @param idAlumnoLibro El idAlumnoLibro de AlumnoLibro.
     */
    public void setIdAlumnoLibro(AlumnoLibroId idAlumnoLibro) {
        this.idAlumnoLibro = idAlumnoLibro;
    }

    /**
     * Obtiene el libro.
     *
     * @return El libro.
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Establece el libro.
     *
     * @param libro El libro.
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Obtiene la fecha asignada.
     *
     * @return La fecha asignada.
     */
    public Date getFechaAsignado() {
        return fechaAsignado;
    }

    /**
     * Establece la fecha asignada.
     *
     * @param fechaAsignado La fecha asignada.
     */
    public void setFechaAsignado(Date fechaAsignado) {
        this.fechaAsignado = fechaAsignado;
    }

    /**
     * Obtiene la fecha limite.
     *
     * @return La fecha limite.
     */
    public Date getFechaLimite() {
        return fechaLimite;
    }

    /**
     * Establece la fecha limite.
     *
     * @param fechaLimite La fecha limite.
     */
    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    /**
     * Método que compara el código hash de dos objetos.
     *
     * @return un código hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.idAlumnoLibro);
        hash = 59 * hash + Objects.hashCode(this.libro);
        hash = 59 * hash + Objects.hashCode(this.fechaAsignado);
        hash = 59 * hash + Objects.hashCode(this.fechaLimite);
        return hash;
    }

    /**
     * Método que compara si un objeto es igual al objeto "AlumnoLibro".
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
        final AlumnoLibro other = (AlumnoLibro) obj;
        if (!Objects.equals(this.idAlumnoLibro, other.idAlumnoLibro)) {
            return false;
        }
        if (!Objects.equals(this.libro, other.libro)) {
            return false;
        }
        if (!Objects.equals(this.fechaAsignado, other.fechaAsignado)) {
            return false;
        }
        if (!Objects.equals(this.fechaLimite, other.fechaLimite)) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve un String con los atributos del AlumnoLibro.
     *
     * @return el String con los atributos de la entidad.
     */
    @Override
    public String toString() {
        return "AlumnoLibro{" + "idAlumnoLibro=" + idAlumnoLibro + ", libro=" + libro + ", fechaAsignado=" + fechaAsignado + ", fechaLimite=" + fechaLimite + '}';
    }
}
