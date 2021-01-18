/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import implementaciones.UsuarioGestionImplementacion;
import interfaces.UsuarioGestion;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controlador de la vista UISignUp que contiene los metodos para definir y
 * controlar su comportamiento.
 *
 * @author Cristina Milea y Nerea Aranguren
 */
public class UISignUpController {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("controladores.UISignUpController");

    /**
     * Atributo estático y constante que guarda los caracteres máximos admitidos
     * en los campos de texto.
     */
    private static final int MAX_LENGHT = 50;

    /**
     * Atributo estático y constante que guarda los caracteres máximos admitidos
     * en el campo de texto de telefono.
     */
    private static final int MAX_LENGHT_TELEFONO = 9;

    /**
     * Atributo estático y constante que guarda el patron correcto de nombre.
     */
    public static final Pattern VALID_NOMBRE = Pattern.compile("^[A-Z\\s]+$", Pattern.CASE_INSENSITIVE);
    /**
     * Atributo estático y constante que guarda el patron correcto de email.
     */
    public static final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    /**
     * Atributo estático y constante que guarda el patron correcto de usuario.
     */
    public static final Pattern VALID_USUARIO = Pattern.compile("^[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    /**
     * Atributo estático y constante que guarda el patron correcto de
     * contraseña.
     */
    public static final Pattern VALID_CONTRASENIA = Pattern.compile("^[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    /**
     * Atributo estático y constante que guarda el patron correcto de telefono.
     */
    public static final Pattern VALID_TELEFONO = Pattern.compile("^[0-9]{9}", Pattern.CASE_INSENSITIVE);

    /**
     * Variable de tipo stage que se usa para visualizar la ventana.
     */
    private Stage stage;

    /**
     * Elemento tipo pane importado de la vista FXML.
     */
    @FXML
    private Pane paneSignUp;
    /**
     * Elemento tipo label importado del FXML que referencia a Título.
     */
    @FXML
    private Label lblTitulo;
    /**
     * Elemento tipo label importado del FXML que referencia a Nombre.
     */
    @FXML
    private Label lblNombre;
    /**
     * Elemento tipo textfield importado del FXML que referencia a Nombre.
     */
    @FXML
    private TextField txtNombre;
    /**
     * Elemento tipo label importado del FXML que referencia a Email.
     */
    @FXML
    private Label lblEmail;
    /**
     * Elemento tipo textfield importado del FXML que referencia a Email.
     */
    @FXML
    private TextField txtEmail;
    /**
     * Elemento tipo label importado del FXML que referencia a Usuario.
     */
    @FXML
    private Label lblUsuario;
    /**
     * Elemento tipo textfield importado del FXML que referencia a Usuario.
     */
    @FXML
    private TextField txtUsuario;
    /**
     * Elemento tipo label importado del FXML que referencia a Contraseña.
     */
    @FXML
    private Label lblContrasenia;
    /**
     * Elemento tipo textfield importado del FXML que referencia a Contraseña.
     */
    @FXML
    private PasswordField txtContrasenia;
    /**
     * Elemento tipo label importado del FXML que referencia a RepiteContraseña.
     */
    @FXML
    private Label lblRepiteContrasenia;
    /**
     * Elemento tipo textfield importado del FXML que referencia a
     * RepiteContraseña.
     */
    @FXML
    private PasswordField txtRepiteContrasenia;
    /**
     * Elemento tipo boton importado del FXML que referencia a Registrarse.
     */
    @FXML
    private Button btnRegistrarse;
    /**
     * Elemento tipo boton importado del FXML que referencia a Volver.
     */
    @FXML
    private Button btnVolver;
    /**
     * Elemento tipo label importado del FXML que referencia a
     * RepiteContraseñaError.
     */
    @FXML
    private Label lblRepiteContraseniaError;
    /**
     * Elemento tipo label importado del FXML que referencia a ContraseñaError.
     */
    @FXML
    private Label lblContraseniaError;
    /**
     * Elemento tipo label importado del FXML que referencia a UsuarioError.
     */
    @FXML
    private Label lblUsuarioError;
    /**
     * Elemento tipo label importado del FXML que referencia a EmailError.
     */
    @FXML
    private Label lblEmailError;
    /**
     * Elemento tipo label importado del FXML que referencia a NombreError.
     */
    @FXML
    private Label lblNombreError;
    /**
     * Elemento tipo label importado del FXML que referencia a NumeroTelefono.
     */
    @FXML
    private Label lblNumeroTelefono;
    /**
     * Elemento tipo textfield importado del FXML que referencia a
     * NumeroTelefono.
     */
    @FXML
    private TextField txtNumeroTelefono;
    /**
     * Elemento tipo label importado del FXML que referencia a
     * NumeroTelefonoError.
     */
    @FXML
    private Label lblNumeroTelefonoError;

    /**
     * Método que establece al escenario del login como escenario principal.
     *
     * @param primaryStage El escenario de Sign Up.
     */
    public void setStage(Stage primaryStage) {
        LOGGER.info("UISignUpController: Estableciendo stage");

        stage = primaryStage;
    }

    /**
     * Método que inicializa el escenario y los componentes del Login.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("UISignUpController: Iniciando stage");

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Sign up");
        stage.setResizable(false);

        stage.onCloseRequestProperty().set(this::cerrarVentana);

        //btnRegistrarse.setDisable(true);

        txtNombre.textProperty().addListener(this::comprobarLongitud);
        txtEmail.textProperty().addListener(this::comprobarLongitud);
        txtUsuario.textProperty().addListener(this::comprobarLongitud);
        txtContrasenia.textProperty().addListener(this::comprobarLongitud);
        txtRepiteContrasenia.textProperty().addListener(this::comprobarLongitud);
        txtNumeroTelefono.textProperty().addListener(this::comprobarLongitud);

        btnRegistrarse.setOnAction(this::botonRegistroPulsado);

        btnVolver.setOnAction(this::botonVolverPulsado);

        stage.show();

        txtNombre.requestFocus();
    }

    /**
     * Método que comprueba que si hay algún campo de texto vacio.
     *
     * @return Variable que indica si hay algún campo de texto vacio.
     */
    private boolean camposVacios() {
        boolean vacio = false;

        if (txtNombre.getText().isEmpty() || txtEmail.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtContrasenia.getText().isEmpty() || txtRepiteContrasenia.getText().isEmpty() || txtNumeroTelefono.getText().isEmpty()) {

            vacio = true;
        }

        return vacio;
    }

    /**
     * Método que habilita y deshabilita el boton de Registrarse en función del
     * metodo 'camposVacios'.
     */
    private void habilitarBotones() {

        if (camposVacios()) {
            btnRegistrarse.setDisable(true);
        } else {
            btnRegistrarse.setDisable(false);
        }
    }

    /**
     * Método que comprueba la longitud de los campos de texto. Si exceden el
     * maximo permitido no recoge el resto de carácteres.
     *
     * @param observable El valor que se observa.
     * @param oldValue El valor antiguo del observable.
     * @param newValue El valor nuevo del observable.
     */
    private void comprobarLongitud(ObservableValue observable, String oldValue, String newValue) {
        if (txtNombre.getText().length() > MAX_LENGHT) {
            String nombre = txtNombre.getText().substring(0, MAX_LENGHT);
            txtNombre.setText(nombre);
        }

        if (txtEmail.getText().length() > MAX_LENGHT) {
            String email = txtEmail.getText().substring(0, MAX_LENGHT);
            txtEmail.setText(email);
        }

        if (txtUsuario.getText().length() > MAX_LENGHT) {
            String usuario = txtUsuario.getText().substring(0, MAX_LENGHT);
            txtUsuario.setText(usuario);
        }

        if (txtContrasenia.getText().length() > MAX_LENGHT) {
            String contrasenia = txtContrasenia.getText().substring(0, MAX_LENGHT);
            txtContrasenia.setText(contrasenia);
        }

        if (txtRepiteContrasenia.getText().length() > MAX_LENGHT) {
            String repiteContrasenia = txtRepiteContrasenia.getText().substring(0, MAX_LENGHT);
            txtRepiteContrasenia.setText(repiteContrasenia);
        }

        if (txtNumeroTelefono.getText().length() > MAX_LENGHT_TELEFONO) {
            String numeroTelefono = txtNumeroTelefono.getText().substring(0, MAX_LENGHT_TELEFONO);
            txtNumeroTelefono.setText(numeroTelefono);
        }

        habilitarBotones();
    }

    /**
     * Método que comprueba que el texto introducido en los campos de texto de
     * Nombre, Email, Usuario, Contraseña y NumeroTelefono cumple con los
     * patrones establecidos, si no lo hace muestra el error en los labels
     * correspondientes.
     *
     * @return Variable que indica si hay algún patrón que no se cumple.
     */
    private boolean comprobarPatrones() {
        boolean error = false;

        if (txtNombre.getText().isEmpty()) {
            lblNombreError.setText("");
        } else {
            Matcher matcher = VALID_NOMBRE.matcher(txtNombre.getText());
            if (!matcher.find()) {
                lblNombreError.setText("El nombre sólo debe contener letras");
                lblNombreError.setTextFill(Color.web("#FF0000"));

                error = true;
            } else {
                lblNombreError.setText("");
            }
        }

        if (txtEmail.getText().isEmpty()) {
            lblEmailError.setText("");
        } else {
            Matcher matcher = VALID_EMAIL.matcher(txtEmail.getText());
            if (!matcher.find()) {
                lblEmailError.setText("Email inválido");
                lblEmailError.setTextFill(Color.web("#FF0000"));

                error = true;
            } else {
                lblEmailError.setText("");
            }
        }

        if (txtUsuario.getText().isEmpty()) {
            lblUsuarioError.setText("");
        } else {
            Matcher matcher = VALID_USUARIO.matcher(txtUsuario.getText());
            if (!matcher.find()) {
                lblUsuarioError.setText("El usuario sólo debe contener letras y números");
                lblUsuarioError.setTextFill(Color.web("#FF0000"));

                error = true;
            } else {
                lblUsuarioError.setText("");
            }
        }

        if (txtContrasenia.getText().isEmpty()) {
            lblContraseniaError.setText("");
        } else {
            Matcher matcher = VALID_CONTRASENIA.matcher(txtContrasenia.getText());
            if (!matcher.find()) {
                lblContraseniaError.setText("Introduce sólo letras y números");
                lblContraseniaError.setTextFill(Color.web("#FF0000"));

                error = true;
            } else {
                lblContraseniaError.setText("");
            }
        }

        if (txtNumeroTelefono.getText().isEmpty()) {
            lblNumeroTelefonoError.setText("");
        } else {
            Matcher matcher = VALID_TELEFONO.matcher(txtNumeroTelefono.getText());
            if (!matcher.find()) {
                lblNumeroTelefonoError.setText("Número de teléfono no válido");
                lblNumeroTelefonoError.setTextFill(Color.web("#FF0000"));

                error = true;
            } else {
                lblNumeroTelefonoError.setText("");
            }
        }

        return error;
    }

    /**
     * Método que comprueba que Contraseña y RepiteContraseña contienen el mismo
     * texto.
     *
     * @return Variable que indica si las contraseñas coinciden o no.
     */
    private boolean comprobarContrasenias() {
        boolean error = false;

        if (!txtContrasenia.getText().equals(txtRepiteContrasenia.getText())) {
            lblRepiteContraseniaError.setText("Las contraseñas no coinciden");
            lblRepiteContraseniaError.setTextFill(Color.web("#FF0000"));

            error = true;
        } else {
            lblRepiteContraseniaError.setText("");
        }

        return error;
    }

    /**
     * Método que carga y abre la venta UIGrupo.
     *
     * @param event El evento de acción.
     */
    private void botonRegistroPulsado(ActionEvent event) {
        LOGGER.info("UISignUpController: Comprobando errores");

        boolean errorPatrones = comprobarPatrones();
        boolean errorContrasenias = comprobarContrasenias();

        if (!errorPatrones && !errorContrasenias) {
            LOGGER.info("UISignUpController: Comprobando si existe el usuario");
            
            UsuarioGestion usuarioGestion = new UsuarioGestionImplementacion();

            usuarioGestion.buscarUsuarioPorLogin("knuspo");

            try {
                LOGGER.info("UISignUpController: Iniciando vista Grupo");
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UIGrupo.fxml"));
                Parent root = (Parent) loader.load();
                UIGrupoController controller = ((UIGrupoController) loader.getController());
                controller.setStage(stage);
                controller.initStage(root);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    /**
     * Método que carga y abre la venta UISignIn.
     *
     * @param event El evento de acción.
     */
    private void botonVolverPulsado(ActionEvent event) {
        LOGGER.info("UISignUpController: Iniciando vista SignIn");

        boolean errorPatrones = comprobarPatrones();

        if (!errorPatrones) {
            LOGGER.info("UISignUpController: Iniciando vista Sign In");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UISignIn.fxml"));
                Parent root = (Parent) loader.load();
                UISignInController controller = ((UISignInController) loader.getController());
                controller.setStage(stage);
                controller.initStage(root);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    /**
     * Cuadro de diálogo que se abre al pulsar la x de la pantalla para
     * confirmar si se quiere cerrar la aplicación.
     *
     * @param event El evento de acción.
     */
    private void cerrarVentana(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Salir");
        alert.setHeaderText(null);
        alert.setContentText("¿Seguro que quieres cerrar la ventana?");

        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get().equals(ButtonType.OK)) {
            stage.close();
            Platform.exit();
        } else {
            event.consume();
            alert.close();
        }
    }
}
