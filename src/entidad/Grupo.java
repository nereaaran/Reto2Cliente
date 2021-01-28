/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase entidad de Grupo con sus respectivos atributos.
 *
 * @author Jonathan Viñan
 *
 */
@XmlRootElement(name = "grupo")
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id del grupo
     */
    private Integer idGrupo;
    /**
     * Nombre del grupo
     */

    private String nombre;
    /**
     * Una descripcion al grupo
     */

    private String descripcion;
    /**
     * La cantidad de alumnos que estan en el grupo
     */
    private Integer numAlumno;
    
    
    private GrupoLibro grupoLibros;
    /**
     * Relacion con la entidad Alumno
     */
    private Alumno alumnos;
    /**
     * Relacion con la entidad Profesor
     */
    private Profesor profesor;

    /**
     * Obtine el id del Grupo
     *
     * @return El valor del id del Grupo
     */
    public Integer getIdGrupo() {
        return idGrupo;
    }

    /**
     * Establece el id para el Grupo
     *
     * @param idGrupo
     */
    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    /**
     * Obtiene el Profesor.
     *
     * @return El profesor.
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * Establece el Profesor.
     *
     * @param profesor El profesor.
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    /**
     * Obtiene la coleccion de grupoLibro.
     *
     * @return Coleccion de grupoLibro.
     */
    public GrupoLibro getGrupoLibros() {
        return grupoLibros;
    }

    /**
     * Establece un grupoLibro
     *
     * @param grupoLibros una coleccion de grupoLibro.
     */
    public void setGrupoLibros(GrupoLibro grupoLibros) {
        this.grupoLibros = grupoLibros;
    }

    /**
     * Obtiene un alumno
     *
     * @return Coleccion de alumno
     */
    public Alumno getAlumnos() {
        return alumnos;
    }

    /**
     * Establece un alumno
     *
     * @param alumnos
     */
    public void setAlumnos(Alumno alumnos) {
        this.alumnos = alumnos;
    }

    /**
     * Obtine el nombre del Grupo
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del grupo
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dscripcion del Grupo
     *
     * @return description
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripcion del grupo
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Establece numAlumno del grupo
     *
     * @param numAlumno
     */
    public void setNumAlumno(Integer numAlumno) {
        this.numAlumno = numAlumno;
    }

    /**
     * Obtine la numAlumno del Grupo
     *
     * @return numAlumno
     */
    public Integer getNumAlumno() {
        return numAlumno;
    }

    /**
     * Implemantacion del metodo hasCode para la entidad.
     *
     * @return Valor integer dej hasCode del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.idGrupo);
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.descripcion);
        hash = 59 * hash + Objects.hashCode(this.numAlumno);
        hash = 59 * hash + Objects.hashCode(this.profesor);
        hash = 59 * hash + Objects.hashCode(this.grupoLibros);
        hash = 59 * hash + Objects.hashCode(this.alumnos);
        return hash;
    }

    /**
     * Metodo qur comprueba el idGrupo de dos entidades de grupo por igualdad
     *
     * @param obj Objeto para comprobar
     * @return True si todos los objetos son iguales
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
        final Grupo other = (Grupo) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.numAlumno, other.numAlumno)) {
            return false;
        }
        return true;
    }

}
