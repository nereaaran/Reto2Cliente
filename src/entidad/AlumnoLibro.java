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

    /**
     * El alumno perteneciente a la relacion de alumnoLibro.
     */
    private Alumno alumno;

    /**
     * El libro perteneciente a la relacion de alumnoLibro.
     */
    private Libro libro;

    /**
     * Fecha en la que se asigna el libro.
     */
    private Date fechaAsignado;

    /**
     * Fecha maxima asignada para el libro.
     */
    private Date fechaLimite;

    /**
     * Obtiene el alumno.
     *
     * @return El alumno.
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * Establece el alumno.
     *
     * @param alumno El alumno.
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
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
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.alumno);
        hash = 89 * hash + Objects.hashCode(this.libro);
        hash = 89 * hash + Objects.hashCode(this.fechaAsignado);
        hash = 89 * hash + Objects.hashCode(this.fechaLimite);
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
        if (!Objects.equals(this.alumno, other.alumno)) {
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
        return "AlumnoLibro{" + "alumno=" + alumno + ", libro=" + libro + ", fechaAsignado=" + fechaAsignado + ", fechaLimite=" + fechaLimite + '}';
    }

}
