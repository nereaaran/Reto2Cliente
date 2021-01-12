/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase de la entidad "Bibliotecario".
 *
 * @author Cristina Milea
 */
@XmlRootElement(name = "bibliotecario")
public class Bibliotecario extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

}
