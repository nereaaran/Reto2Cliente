/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ResourceBundle;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:GrupoFacadeREST
 * [entidad.grupo]<br>
 * USAGE:
 * <pre>
 *        GrupoRESTClient client = new GrupoRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Jonathan Viñan
 */
public class GrupoRESTClient {

    /**
     * Objeto de tipo webTarget.
     */
    private WebTarget webTarget;
    /**
     * Objeto de tipo Client.
     */
    private Client client;
    /**
     * Coge el URI de un archivo de propiedades.
     */
    private static final String BASE_URI = ResourceBundle.getBundle("archivos.parametros").getString("RESTFUL_URI");

    /**
     * Constructor que crea un cliente web RESTful y establece la ruta del
     * objeto webTarget asociado al cliente.
     */
    public GrupoRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("grupo");
    }

    /**
     * Crea una reperesentación XML de la entidad Grupo y la manda al servicio
     * web grupo RESTful como petición para modificarlo.
     *
     * @param requestEntity Objeto con los datos a ser modificados.
     * @throws ClientErrorException Si hay un error durante el proceso. El error
     * va envuelto en una respuesta de error de HTTP.
     */
    public void edit(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Obtiene una reperesentación XML de la entidad Grupo del servicio web
     * grupo RESTful y lo devuelve como un objeto de tipo genérico.
     *
     * @param <T> Clase de tipo generico.
     * @param responseType La clase objeto de la instancia de retorno.
     * @param id La id de la instancia del lado servidor.
     * @return El objeto con los datos.
     * @throws ClientErrorException Si hay un error durante el proceso. El error
     * va envuelto en una respuesta de error de HTTP.
     */
    public <T> T find(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Crea una reperesentación XML de la entidad Grupo y la manda al servicio
     * web grupo RESTful como petición para crearlo.
     *
     * @param requestEntity Objeto con los datos a ser creados.
     * @throws ClientErrorException Si hay un error durante el proceso. El error
     * va envuelto en una respuesta de error de HTTP.
     */
    public void create(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Manda al servicio web grupo RESTful una petición para eliminar un grupo
     * identificado por el id.
     *
     * @param id El id de la entidad bibliotecario que se eliminará.
     * @throws ClientErrorException Si hay un error durante el proceso. El error
     * va envuelto en una respuesta de error de HTTP.
     */
    public void remove(Integer id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
    }

    /**
     * Obtiene una lista de reperesentaciónes XML de la entidad Grupo del
     * servicio web grupo RESTful y lo devuelve como un objeto de tipo genérico.
     *
     * @param <T> Clase de tipo generico.
     * @param responseType La clase objeto de la instancia de retorno.
     * @return Coleccion de objetos con los datos.
     * @throws ClientErrorException Si hay un error durante el proceso. El error
     * va envuelto en una respuesta de error de HTTP.
     */
    public <T> T listarGrupos(GenericType<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Obtiene una lista de reperesentaciónes XML de la entidad Grupo del
     * servicio web grupo RESTful y lo devuelve como un objeto de tipo genérico.
     *
     * @param <T> Clase de tipo generico.
     * @param responseType La clase objeto de la instancia de retorno.
     * @param nombre Recoge el el nobre de grupo
     * @return Coleccion de objetos con los datos.
     * @throws ClientErrorException Si hay un error durante el proceso. El error
     * va envuelto en una respuesta de error de HTTP.
     */
    public <T> T listarGrupoPorNombre(GenericType<T> responseType, String nombre) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("nombre/{0}", new Object[]{nombre}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Cierra el servicio web RESTful del cliente.
     */
    public void close() {
        client.close();
    }
}