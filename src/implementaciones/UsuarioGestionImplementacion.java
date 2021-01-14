/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import entidad.Alumno;
import entidad.Usuario;
import interfaces.UsuarioGestion;
import java.util.Collection;
import javax.ws.rs.ClientErrorException;
import rest.UsuarioRESTClient;

/**
 *
 * @author 2dam
 */
public class UsuarioGestionImplementacion implements UsuarioGestion {
//REST users web client

    private UsuarioRESTClient webClient;

    public UsuarioGestionImplementacion() {
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
    public Alumno find(Integer id) throws ClientErrorException {
        Alumno usuario = null;
        usuario = this.webClient.find(Alumno.class, id);
        System.out.println("ENCONTRADO");
        
        return usuario;
    }

    @Override
    public Collection<Usuario> buscarUsuarioPorLogin(String login) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
