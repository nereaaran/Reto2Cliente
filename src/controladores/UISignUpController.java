/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Controlador de la vista UISignUp que contiene los metodos para definir y
 * controlar su comportamiento.
 *
 * @author Nerea Aranguren
 */
public class UISignUpController {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("controladores.UISignInController");

    private static final int MAX_LENGHT = 50;
    private static final int MAX_LENGHT_TELEFONO = 9;

    public static final Pattern VALID_NOMBRE = Pattern.compile("^[A-Z\\s]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_USUARIO = Pattern.compile("^[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_CONTRASENA = Pattern.compile("^[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_TELEFONO = Pattern.compile("^[0-9]{9}", Pattern.CASE_INSENSITIVE);

    /**
     * Lista de elementos importados de la vista FXML que representan objetos.
     */
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
    private Label lblContrasena;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Label lblRepiteContrasena;
    @FXML
    private PasswordField txtRepiteContrasena;
    @FXML
    private Button btnRegistrarse;
    @FXML
    private Button btnVolver;
    @FXML
    private Label lblRepiteContrasenaError;
    @FXML
    private Label lblContrasenaError;
    @FXML
    private Label lblUsuarioError;
    @FXML
    private Label lblEmailError;
    @FXML
    private Label lblNombreError;
    @FXML
    private Label lblNumeroTelefono;
    @FXML
    private TextField txtNumeroTelefono;
    @FXML
    private Label lblNumeroTelefonoError;

    /**
     * Variable de tipo stage que se usa para visualizar la ventana
     */
    private Stage stage;

    /**
     * Método que establece al escenario del login como escenario principal.
     *
     * @param primaryStage El escenario principal.
     */
    public void setStage(Stage primaryStage) {
        LOGGER.info("SignUp Controlador: Estableciendo stage");

        stage = primaryStage;
    }

    /**
     * Método que inicializa el escenario y los componentes del Login.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("SignUp Controlador: Iniciando stage principal");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.setResizable(false);

        txtNombre.requestFocus();
        btnRegistrarse.setDisable(true);

        txtNombre.textProperty().addListener(this::comprobarLongitud);
        txtEmail.textProperty().addListener(this::comprobarLongitud);
        txtUsuario.textProperty().addListener(this::comprobarLongitud);
        txtContrasena.textProperty().addListener(this::comprobarLongitud);
        txtRepiteContrasena.textProperty().addListener(this::comprobarLongitud);
        txtNumeroTelefono.textProperty().addListener(this::comprobarLongitud);

        btnRegistrarse.setOnAction(this::handleBotonRegistro);
        btnVolver.setOnAction(this::handleBotonVolver);

        stage.show();
    }

    private void handleBotonRegistro(ActionEvent event) {
        //LOGGER.info("SignIn Controlador: Iniciando vista Sign Up");

        //boolean errorPatrones = comprobarPatronNombreCompleto();
        boolean errorContrasenas = comprobarContrasenasCoinciden();
        boolean errorNombre = comprobarPatronNombreCompleto();
        boolean errorEmail = comprobarPatronEmail();
        boolean errorUsuario = comprobarPatronUsuario();
        boolean errorContrasena = comprobarPatronContrasena();
        boolean errorTelefono = comprobarPatronNumeroTelefono();

        if(!errorContrasenas && !errorNombre && !errorEmail && !errorUsuario && !errorContrasena && !errorTelefono) {
        //if (!errorPatrones && !errorContrasenas) {
            lblTitulo.setText("OK"); //quitar mas tarde
        } else {
            lblTitulo.setText("NO"); //quitar mas tarde
        }
    }

    private boolean camposVacios() {
        boolean vacios = false;

        if (txtNombre.getText().isEmpty() || txtEmail.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtContrasena.getText().isEmpty() || txtRepiteContrasena.getText().isEmpty() || txtNumeroTelefono.getText().isEmpty()) {
            vacios = true;
        }

        return vacios;
    }

    private void habilitarBotonRegistrarse() {
        if (camposVacios()) {
            btnRegistrarse.setDisable(true);
        } else {
            btnRegistrarse.setDisable(false);
        }
    }

    private void comprobarLongitud(ObservableValue observable, String oldValue, String newValue) {
        if (txtNombre.getText().length() > MAX_LENGHT) {
            String nombre = txtNombre.getText().substring(0, MAX_LENGHT);
            txtNombre.setText(nombre);
        } else {
            lblNombreError.setText("");
        }

        if (txtEmail.getText().length() > MAX_LENGHT) {
            String email = txtEmail.getText().substring(0, MAX_LENGHT);
            txtEmail.setText(email);
        } else {
            lblEmailError.setText("");
        }

        if (txtUsuario.getText().length() > MAX_LENGHT) {
            String usuario = txtUsuario.getText().substring(0, MAX_LENGHT);
            txtUsuario.setText(usuario);
        } else {
            lblUsuarioError.setText("");
        }

        if (txtContrasena.getText().length() > MAX_LENGHT) {
            String contrasena = txtContrasena.getText().substring(0, MAX_LENGHT);
            txtContrasena.setText(contrasena);
        } else {
            lblContrasenaError.setText("");
        }

        if (txtRepiteContrasena.getText().length() > MAX_LENGHT) {
            String repiteContrasena = txtRepiteContrasena.getText().substring(0, MAX_LENGHT);
            txtRepiteContrasena.setText(repiteContrasena);
        } else {
            lblRepiteContrasenaError.setText("");
        }

        if (txtNumeroTelefono.getText().length() > MAX_LENGHT_TELEFONO) {
            String numeroTelefono = txtNumeroTelefono.getText().substring(0, MAX_LENGHT_TELEFONO);
            txtNumeroTelefono.setText(numeroTelefono);
        } else {
            lblNumeroTelefonoError.setText("");
        }

        habilitarBotonRegistrarse();
    }

    private boolean comprobarPatronNombreCompleto() {
        boolean error = false;

        if (txtNombre.getText().isEmpty()) {
            lblNombre.setText("");
        } else {
            Matcher matcher = VALID_NOMBRE.matcher(txtNombre.getText());
            if (!matcher.find()) {
                lblNombreError.setText("El nombre solo debe contener letras");
                lblNombreError.setTextFill(Color.web("#FF0000"));

                error = true;
            }
        }

        return error;
    }

    private boolean comprobarPatronEmail() {
        boolean error = false;

        if (txtEmail.getText().isEmpty()) {
            lblEmailError.setText("");
        } else {
            Matcher matcher = VALID_EMAIL.matcher(txtEmail.getText());
            if (!matcher.find()) {
                lblEmailError.setText("Email inválido");
                lblEmailError.setTextFill(Color.web("#FF0000"));

                error = true;
            }
        }

        return error;
    }

    private boolean comprobarPatronUsuario() {
        boolean error = false;

        if (txtUsuario.getText().isEmpty()) {
            lblUsuarioError.setText("");
        } else {
            Matcher matcher = VALID_USUARIO.matcher(txtUsuario.getText());
            if (!matcher.find()) {
                lblUsuarioError.setText("El usuario solo debe contener letras y números");
                lblUsuarioError.setTextFill(Color.web("#FF0000"));

                error = true;
            }
        }

        return error;
    }

    private boolean comprobarPatronContrasena() {
        boolean error = false;

        if (txtContrasena.getText().isEmpty()) {
            lblContrasenaError.setText("");
        } else {
            Matcher matcher = VALID_CONTRASENA.matcher(txtContrasena.getText());
            if (!matcher.find()) {
                lblContrasenaError.setText("La contrasena solo debe contener letras y números");
                lblContrasenaError.setTextFill(Color.web("#FF0000"));

                error = true;
            }
        }

        return error;
    }

    private boolean comprobarContrasenasCoinciden() {
        boolean error = false;

        if (!txtContrasena.getText().equals(txtRepiteContrasena.getText())) {
            lblRepiteContrasenaError.setText("Las contraseñas no coinciden");
            lblRepiteContrasenaError.setTextFill(Color.web("#FF0000"));
        } else {
            lblRepiteContrasenaError.setText("");
        }

        return error;
    }

    private boolean comprobarPatronNumeroTelefono() {
        boolean error = false;

        if (txtNumeroTelefono.getText().isEmpty()) {
            lblNumeroTelefonoError.setText("");
        } else {
            Matcher matcher = VALID_TELEFONO.matcher(txtNumeroTelefono.getText());
            if (!matcher.find()) {
                lblNumeroTelefonoError.setText("Número de teléfono inválido");
                lblNumeroTelefonoError.setTextFill(Color.web("#FF0000"));

                error = true;
            }
        }

        return error;
    }

    private boolean comprobarPatrones() {
        boolean error = false;

        if (comprobarPatronNombreCompleto() || comprobarPatronEmail() || comprobarPatronUsuario() || comprobarPatronContrasena() || comprobarPatronNumeroTelefono()) {
            error = true;
        }

        return error;
    }

    /**
     * Método que carga y abre la venta UISignIn.
     *
     * @param event El evento de acción.
     */
    private void handleBotonVolver(ActionEvent event) {
        LOGGER.info("SignUp Controlador: Iniciando vista SignIn");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UISignIn.fxml"));
            Parent root = (Parent) loader.load();
            UISignInController controller = ((UISignInController) loader.getController());
            //controller.setStage(stage);
            //controller.initStage(root);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
