/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import entidad.GrupoLibro;
import interfaces.GrupoLibroGestion;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import rest.GrupoLibroRESTClient;

/**
 *
 * @author JonY
 */
public class GrupoLibroGestionImplementation implements GrupoLibroGestion{
     private GrupoLibroRESTClient webClient;
    private static final Logger LOGGER = Logger.getLogger("GrupoGestionImplementation");

    /**
     * Constructor que sirve para acceder al servicio REST del lado servidor.
     *
     */
    public GrupoLibroGestionImplementation() {
        webClient = new GrupoLibroRESTClient();
    }

    
    @Override
    public void create(GrupoLibro grupolibro) {
       try {
            LOGGER.log(Level.INFO, "GRUPO: Creando un grupo");
            //Send user data to web client for creation. 
            webClient.create(grupolibro);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        } }

    @Override
    public void edit(GrupoLibro grupolibro) {
        try {
            LOGGER.info("GrupoGestionImplementation: Editando grupo");
            this.webClient.edit(grupolibro);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void remove(Integer id) {
         try {
            LOGGER.info("GeupoGestionImplementation: Eliminando grupo");

            this.webClient.remove(id.toString());
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        } }

    @Override
    public GrupoLibro find(Integer id) {
        GrupoLibro grupoLibros = null;
        try {
            LOGGER.info("Grupo: buscando grupo por id");
            //grupos = this.webClient.find(responseType, id);
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
        return grupoLibros;}

   
    
}
