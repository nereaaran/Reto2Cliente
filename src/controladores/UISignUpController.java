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

/**
 * FXML Controller class
 *
 * @author nerea
 */
public class UISignUpController implements Initializable {

    @FXML
    private Pane paneSignUp;
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
    private Label lblContraseña;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private Label lblRepiteContraseña;
    @FXML
    private PasswordField txtRepiteContraseña;
    @FXML
    private Button btnRegistrarse;
    @FXML
    private Button btnVolver;
    @FXML
    private Label lblContraseñaRepiteError;
    @FXML
    private Label lblContraseñaError;
    @FXML
    private Label lblUsuarioError;
    @FXML
    private Label lblEmailError;
    @FXML
    private Label lblNombreError;
    @FXML
    private Label lblNumeroTelefono;
    @FXML
    private PasswordField txtNumeroTelefono;
    @FXML
    private Label lblNumeroTelefonoError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
