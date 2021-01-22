/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ProfesorFacadeREST
 * [entidad.profesor]<br>
 * USAGE:
 * <pre>
 *        ProfesorRESTClient client = new ProfesorRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Cristina Milea y Nerea Aranguren
 */
public class ProfesorRESTClient {

    /**
     * Objeto de tipo webTarget.
     */
    private final WebTarget webTarget;
    /**
     * Objeto de tipo Client.
     */
    private final Client client;
    /**
     * Coge el URI de un archivo de propiedades.
     */
    private static final String BASE_URI = "http://localhost:8080/Reto2Servidor/webresources";

    /**
     * Constructor que crea un cliente web RESTful y establece la ruta del
     * objeto webTarget asociado al cliente.
     */
    public ProfesorRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("profesor");
    }

    /**
     * Crea una reperesentación XML de la entidad Profesor y la manda al
     * servicio web profesor RESTful como petición para modificarlo.
     *
     * @param requestEntity Objeto con los datos a ser modificados.
     * @throws ClientErrorException Si hay un error durante el proceso. El error
     * va envuelto en una respuesta de error de HTTP.
     */
    public void edit(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Obtiene una reperesentación XML de la entidad Profesor del servicio web
     * profesor RESTful y lo devuelve como un objeto de tipo genérico.
     *
     * @param <T> Clase de tipo generico.
     * @param responseType La clase objeto de la instancia de retorno.
     * @param id La id de la instancia del lado servidor.
     * @return El objeto con los datos.
     * @throws ClientErrorException Si hay un error durante el proceso. El error
     * va envuelto en una respuesta de error de HTTP.
     */
    public <T> T find(Class<T> responseType, Integer id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Crea una reperesentación XML de la entidad Profesor y la manda al
     * servicio web profesor RESTful como petición para crearlo.
     *
     * @param requestEntity Objeto con los datos a ser creados.
     * @throws ClientErrorException Si hay un error durante el proceso. El error
     * va envuelto en una respuesta de error de HTTP.
     */
    public void create(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Manda al servicio web profesor RESTful una petición para eliminar un
     * profesor identificado por el id.
     *
     * @param id El id de la entidad profesor que se eliminará.
     * @throws ClientErrorException Si hay un error durante el proceso. El error
     * va envuelto en una respuesta de error de HTTP.
     */
    public void remove(Integer id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
    }

    /**
     * Obtiene una lista de reperesentaciónes XML de la entidad Profesor del
     * servicio web profesor RESTful y lo devuelve como un objeto de tipo
     * genérico.
     *
     * @param <T> Clase de tipo generico.
     * @param responseType La clase objeto de la instancia de retorno.
     * @return Coleccion de objetos con los datos.
     * @throws ClientErrorException Si hay un error durante el proceso. El error
     * va envuelto en una respuesta de error de HTTP.
     */
    public <T> T buscarTodosLosProfesores(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Cierra el servicio web RESTful del cliente.
     */
    public void close() {
        client.close();
    }
}
