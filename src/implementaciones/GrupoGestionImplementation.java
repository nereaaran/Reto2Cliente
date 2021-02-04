/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import entidad.Grupo;
import interfaces.GrupoGestion;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import rest.GrupoRESTClient;

/**
 * Clase que implementa la interfaz GrupoGestion usando un cliente web RESTful.
 *
 * @author Jonathan Viñan.
 */
public class GrupoGestionImplementation implements GrupoGestion {

    private GrupoRESTClient webClient;
    private static final Logger LOGGER = Logger.getLogger("GrupoGestionImplementation");

    /**
     * Constructor que sirve para acceder al servicio REST del lado servidor.
     *
     */
    public GrupoGestionImplementation() {
        webClient = new GrupoRESTClient();
    }

    @Override
    public void create(Grupo grupo) throws ClientErrorException {
        try {
            LOGGER.log(Level.INFO, "GRUPO: Creando un grupo");
            //Send user data to web client for creation. 
            webClient.create(grupo);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void edit(Grupo grupo) throws ClientErrorException {
        try {
            LOGGER.info("GrupoGestionImplementation: Editando grupo");
            this.webClient.edit(grupo);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void remove(Integer id) throws ClientErrorException {
        try {
            LOGGER.info("GeupoGestionImplementation: Eliminando grupo");

            this.webClient.remove(id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public Grupo find(Integer id) throws ClientErrorException {
        Grupo grupos = null;
        try {
            LOGGER.info("Grupo: buscando grupo por id");
            //grupos = this.webClient.find(responseType, id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
        return grupos;
    }

    @Override
    public Collection<Grupo> listarGrupoPorNombre(String nombre) throws ClientErrorException {
        Collection<Grupo> grupo = null;
        try {
            LOGGER.info("GrupoGestionImplementation: Buscando grupos por el nombre");
            grupo = this.webClient.listarGrupoPorNombre(new GenericType<Collection<Grupo>>() {
            }, nombre);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
        return grupo;
    }

    @Override
    public Collection<Grupo> listarGrupos() throws ClientErrorException {
        Collection<Grupo> grupo = null;

        try {
            LOGGER.info("GrupoGestionImplementation: Buscandoo todos los grupos");

            grupo = webClient.listarGrupos(new GenericType<Collection<Grupo>>() {
            });
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }

        return grupo;
    }

}
