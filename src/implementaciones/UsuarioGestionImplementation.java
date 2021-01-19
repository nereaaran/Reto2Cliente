/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import entidad.Usuario;
import interfaces.UsuarioGestion;
import java.util.Collection;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import rest.UsuarioRESTClient;

/**
 *
 * @author 2dam
 */
public class UsuarioGestionImplementation implements UsuarioGestion {

    private final UsuarioRESTClient webClient;

    public UsuarioGestionImplementation() {
        webClient = new UsuarioRESTClient();
    }

    @Override
    public void create(Usuario usuario) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Usuario usuario) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer id) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario find(Integer id) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Usuario> buscarUsuarioPorLogin(String login) throws ClientErrorException {
        Collection<Usuario> usuario = webClient.buscarUsuarioPorLogin(new GenericType<Collection<Usuario>>() {}, login);
        
        System.out.println("ENCONTRADO");

        return usuario;
    }

    @Override
    public Collection<Usuario> buscarUsuarioPorEmail(String email) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Usuario> buscarLoginYContrasenia(String login, String contrasenia) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Usuario> consultarTodosAlumnos() throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Usuario> buscarAlumnoPorNombre(String nombre) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}