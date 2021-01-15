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
 * @author 2dam
 */
public class UIMiPerfilController implements Initializable {

    @FXML
    private Pane paneMiPerfil;
    @FXML
    private Rectangle idRectangle;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblUsuario;
    @FXML
    private Button btnVolver;
    @FXML
    private Label lblTelefono;
    @FXML
    private Label lblContraseñaActual;
    @FXML
    private Label lblContraseñaNueva;
    @FXML
    private PasswordField txtContrasenaNueva;
    @FXML
    private Label lblRepetirNuevaContraseña;
    @FXML
    private PasswordField txtRepiteNuevaContrasena;
    @FXML
    private Button btnCambiarContraseña;
    @FXML
    private Label lblContraseñaNuevaRepetirError;
    @FXML
    private Label lblContraseñaNuevaError;
    @FXML
    private Label lblContraseñaActualError;
    @FXML
    private Label lblContrasenaCambiada;
    @FXML
    private Label lblCambioContraseña;
    @FXML
    private TextField txtNombreProfesor;
    @FXML
    private PasswordField txtContrasenaActual;
    @FXML
    private TextField txtEmailProfesor;
    @FXML
    private TextField txtUsuarioProfesor;
    @FXML
    private TextField txtTelefonoProfesor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
