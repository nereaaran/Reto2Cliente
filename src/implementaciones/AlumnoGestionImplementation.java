/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import entidad.Alumno;
import interfaces.AlumnoGestion;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import rest.AlumnoRESTClient;
import seguridad.CifradoAsimetrico;

/**
 * Clase que implementa la interfaz AlumnoGestion usando un cliente web RESTful.
 *
 * @author Cristina Milea y Nerea Aranguren
 */
public class AlumnoGestionImplementation implements AlumnoGestion {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("implementaciones.AlumnoGestionImplementation");

    /**
     * Cliente web de REST alumno.
     */
    private final AlumnoRESTClient webClient;

    /**
     * Constructor que sirve para acceder al servicio REST del lado servidor.
     */
    public AlumnoGestionImplementation() {
        webClient = new AlumnoRESTClient();
    }

    /**
     * Cifra la contraseña con la clave publica.
     *
     * @param contrasena La contraseña del usuario.
     * @return La contraseña cifra y en hexadecimal.
     */
    private String cifrarContrasena(String contrasena) {
        LOGGER.info("AlumnoGestionImplementation: Cifrando contraseña");
        CifradoAsimetrico cifrar = new CifradoAsimetrico();
        return cifrar.cifrarConClavePublica(contrasena);
    }

    /**
     * Manda una petición REST de tipo create al servidor y se crea un nuevo
     * alumno.
     *
     * @param alumno el alumno que se creará.
     */
    @Override
    public void create(Alumno alumno) {
        alumno.setPassword(cifrarContrasena(alumno.getPassword()));

        try {
            LOGGER.info("AlumnoGestionImplementation: Creando alumno");

            webClient.create(alumno);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST de tipo edit al servidor y modifica el alumno.
     *
     * @param alumno el alumno que se modificará.
     */
    @Override
    public void edit(Alumno alumno) {
        alumno.setPassword(cifrarContrasena(alumno.getPassword()));
        
        try {
            LOGGER.info("AlumnoGestionImplementation: Editando alumno");

            webClient.edit(alumno);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST de tipo remove al servidor y elimina el alumno
     * por el id.
     *
     * @param id el id del alumno que se eliminará.
     */
    @Override
    public void remove(Integer id) {
        try {
            LOGGER.info("AlumnoGestionImplementation: Borrando alumno");

            webClient.remove(id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Manda una petición REST de tipo find al servidor y busca el alumno por el
     * id.
     *
     * @param id el id del alumno por el que buscará.
     */
    @Override
    public Alumno find(Integer id) {
        Alumno alumno = null;
        try {
            LOGGER.info("AlumnoGestionImplementation: Buscando alumno por id");

            alumno = webClient.find(Alumno.class, id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }

        return alumno;
    }

}
