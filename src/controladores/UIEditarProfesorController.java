/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author nerea
 */
public class UIEditarProfesorController implements Initializable {

    @FXML
    private Pane paneMiPerfil;
    @FXML
    private Rectangle idRectangle;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblNombre;
    @FXML
    private TextField txtNombre;
    @FXML
    private Label lblEmail;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label lblUsuario;
    @FXML
    private TextField txtUsuario;
    @FXML
    private Button btnVolver;
    @FXML
    private Label lblUsuarioError;
    @FXML
    private Label lblEmailError;
    @FXML
    private Label lblNombreError;
    @FXML
    private Label lblTelefono;
    @FXML
    private PasswordField txtTelefono;
    @FXML
    private Label lblTelefonoError;
    @FXML
    private Label lblContraseñaActual;
    @FXML
    private TextField txtContraseñaActual;
    @FXML
    private Label lblContraseñaNueva;
    @FXML
    private PasswordField txtContraseñaNueva;
    @FXML
    private Label lblRepetirNuevaContraseña;
    @FXML
    private PasswordField txtRepetirNuevaContraseña;
    @FXML
    private Button btnCambiarContraseña;
    @FXML
    private Label lblContraseñaNuevaRepetirError;
    @FXML
    private Label lblContraseñaNuevaError;
    @FXML
    private Label lblContraseñaActualError;
    @FXML
    private Label lblContraseñaCambiada;
    @FXML
    private Label lblCambioContraseña;
    @FXML
    private Button btnGuardarCambios;
    @FXML
    private Label lblCambiosGuardados;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
