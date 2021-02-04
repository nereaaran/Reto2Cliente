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

/**
 * Clase entidad de Profesor con sus respectivos atributos.
 *
 * @author Jonathan Viñan
 */
@XmlRootElement(name = "profesor")
public class Profesor extends Usuario implements Serializable {

    /**
     * Numero de telefono del profesor.
     */
    private Integer telefono;

    private Collection<Grupo> grupos;

   // @XmlElement(name="")
    public Collection<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Collection<Grupo> grupos) {
        this.grupos = grupos;
    }
    
   
    /**
     * Obtiene el telefono.
     *
     * @return telefono.
     */
    public Integer getTelefono() {
        return telefono;
    }

    /**
     * Establece el telefono
     *
     * @param telefono que almacenamos
     */
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    /**
     * Método que compara el código hash de dos objetos.
     *
     * @return el código hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.telefono);
        return hash;
    }

    /**
     * Método que compara si un objeto es igual al objeto "Profesor".
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
        final Profesor other = (Profesor) obj;
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String toString = super.toString();
        return toString + "\n" + "Profesor{" + "telefono=" + telefono + '}';
    }
}
