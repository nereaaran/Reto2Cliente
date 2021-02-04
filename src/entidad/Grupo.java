/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
     * Id para el grupo
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
    
    /**
     * Relacion 1:N de la entidad Grupo con la entidad GrupoLibro
     */
    private Collection<GrupoLibro> grupoLibros;
    /**
     * Relacion N:M con la entidad Alumno
     */
    private Collection<Alumno> alumnos;   
    /**
     * Relacion N:1 de la entidad Profesor
     */
    private Profesor profesor;
    /**
     * Obtine el id del Grupo
     * @return El valor del id del Grupo
     */
    public Integer getIdGrupo() {
        return idGrupo;
    }
    /**
     * Establece el id para el Grupo
     * @param idGrupo el id del grupo.
     */
    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }
    /**
     * Obtine el nombre del Grupo
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Establece el nombre del grupo
     * @param nombre el nombre del grupo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene la dscripcion del Grupo
     * @return description
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Establece la descripcion del grupo
     * @param descripcion la descripcion del grupo.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Obtine la numAlumno del Grupo
     * @return numAlumno
     */
    public Integer getNumAlumno() {
        return numAlumno;
    }
    /**
     * Establece numAlumno del grupo
     * @param numAlumno el numero de alumnos.
     */
    public void setNumAlumno(Integer numAlumno) {
        this.numAlumno = numAlumno;
    }  
    /**
     * Obtiene el Profesor.
     * @return El profesor.
     */
    public Profesor getProfesor() {
        return profesor;
    }
    /**
     * Establece el Profesor.
     * @param profesor El profesor.
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    /**
     * Obtiene la coleccion de grupoLibro.
     * @return Coleccion de grupoLibro.
     */
    public Collection<GrupoLibro> getGrupoLibros() {
        return grupoLibros;
    }
    /**
     * Establece una coleecion de grupoLibro
     * @param grupoLibros una coleccion de grupoLibro.
     */
    public void setGrupoLibros(Collection<GrupoLibro> grupoLibros) {
        this.grupoLibros = grupoLibros;
    }
    /**
     * Obtiene una colecion de alumno
     * @return Coleccion de alumno
     */
    //@XmlElement(name = "")
    public Collection<Alumno> getAlumnos() {
        return alumnos;
    }
    /**
     * Establece una coleccion de alumno
     * @param alumnos los alumnos.
     */
    public void setAlumnos(Collection<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    /**
     * Implemantacion del metodo hasCode para la entidad.
     * @return Valor integer dej hasCode del objeto.
     */
     @Override    
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idGrupo);
        return hash;
    }

    /**
     * Metodo qur comprueba el idGrupo de dos entidades de grupo por igualdad
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
        if (!Objects.equals(this.idGrupo, other.idGrupo)) {
            return false;
        }
        return true;
    }

    /**
     * Meetodo que devuelve un string con los datos del Grupo.
     * @return un string del objeto Grupo.
     */
    @Override
    public String toString() {
        return "Grupo{" + "idGrupo=" + idGrupo + ", nombre=" + nombre
                + ", descripcion=" + descripcion + ", numAlumno=" + numAlumno + '}';
    }


}