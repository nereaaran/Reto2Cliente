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
import java.util.regex.Pattern;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controlador de la vista UISignIn que contiene los metodos para definir y
 * controlar su comportamiento.
 *
 * @author Nerea Aranguren
 */
public class UISignInController {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("controladores.UISignInController");

    /**
     * Variable que guarda los carácteres máximos de los campos de texto.
     */
    private static final int MAX_LENGHT = 50;
    /**
     * Variable que guarda el patrón de carácteres aceptables para la
     * contraseña.
     */
    public static final Pattern VALID_PASSWORD = Pattern.compile("^[a-zA-Z0-9]+$");
    /**
     * Lista de elementos importados de la vista FXML que representan objetos.
     */
    @FXML
    private Pane paneSignIn;
    @FXML
    private Label lblUsuario;
    @FXML
    private TextField txtUsuario;
    @FXML
    private Label lblContraseña;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Button btnRegistrate;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblNoTienesCuenta;
    @FXML
    private Label lblContraseñaError;
    @FXML
    private Label lblUsuarioError;
    @FXML
    private Hyperlink linkContraseñaOlvidada;
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
        LOGGER.info("SignIn Controlador: Estableciendo stage");

        stage = primaryStage;
    }

    /**
     * Método que inicializa el escenario y los componentes del Login.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("SignIn Controlador: Iniciando stage principal");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign In");
        stage.setResizable(false);

        txtUsuario.requestFocus();
        btnIniciarSesion.setDisable(true);

        btnIniciarSesion.setOnAction(this::handleBotonIniciarSesion);
        btnRegistrate.setOnAction(this::handleBotonRegistro);
        linkContraseñaOlvidada.setOnAction(this::handleBotonContraseñaOlvidada);

        txtUsuario.textProperty().addListener(this::handleTextoCambiado);
        txtContraseña.textProperty().addListener(this::handleTextoCambiado);

        stage.show();
    }

    private void handleBotonIniciarSesion(ActionEvent event) {
        LOGGER.info("SignIn Controlador: Pulsado boton Iniciar sesion");
        
        
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UIMiPerfil.fxml"));
            Parent root = (Parent) loader.load();
            UIMiPerfilController controller = ((UIMiPerfilController) loader.getController());
            controller.setStage();
            controller.initStage(root);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Se llama cuando se modifica cualquier campo de texto. Comprueba que los
     * campos de texto no estan vacios y no superan los 50 caracteres. Si se
     * cumplen los requisitos se habilita el boton de Iniciar Sesion, si no se
     * cumplen lo deshabilita.
     *
     * @param observable El valor que se observa.
     * @param oldValue El valor antiguo del observable.
     * @param newValue El valor nuevo del observable.
     */
    private void handleTextoCambiado(ObservableValue observable, String oldValue, String newValue) {
        // Identifica el TextField que lo ha llamado
        StringProperty textProperty = (StringProperty) observable;
        // Guarda el textField
        TextField changedTextField = (TextField) textProperty.getBean();

        if (!textFieldOver50(changedTextField) && !txtUsuario.getText().isEmpty() && !txtContraseña.getText().isEmpty()) {
            btnIniciarSesion.setDisable(false);
        } else {
            btnIniciarSesion.setDisable(true);
        }
    }

    /**
     * Comprueba que el texto introducido no supera los 50 caracteres. Si se
     * supera no muestra ni recoge los nuevos caracteres introducidos.
     *
     * @param changedTextField El campo de texto modificado.
     * @return Variable indicando si supera los 50 caracteres o no.
     */
    private boolean textFieldOver50(TextField changedTextField) {
        boolean over50 = false;

        if (changedTextField.getText().length() > MAX_LENGHT) {
            String text = changedTextField.getText().substring(0, MAX_LENGHT);
            changedTextField.setText(text);
            over50 = true;
        }
        return over50;
    }

    /**
     * Método que carga y abre la venta UIRestaurarContrasenia.
     *
     * @param event El evento de acción.
     */
    private void handleBotonContraseñaOlvidada(ActionEvent event) {
        LOGGER.info("SignIn Controlador: Iniciando vista Restaurar Contraseña");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UIRestaurarContrasenia.fxml"));
            Parent root = (Parent) loader.load();
            UIRestaurarContraseniaController controller = ((UIRestaurarContraseniaController) loader.getController());
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Método que carga y abre la venta UISignUp.
     *
     * @param event El evento de acción.
     */
    private void handleBotonRegistro(ActionEvent event) {
        LOGGER.info("SignIn Controlador: Iniciando vista Sign Up");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UISignUp.fxml"));
            Parent root = (Parent) loader.load();
            UISignUpController controller = ((UISignUpController) loader.getController());
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Ventana de alerta que sale para confirmar o negar el cambio de pantalla.
     *
     * @return Devuelve el resultado de la elección.
     */
    private boolean mostrarAlertConfirmation() {
        boolean confirm = false;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Sign In");
        alert.setContentText("Seguro que quieres cerrar la ventana?");
        Optional<ButtonType> respuesta = alert.showAndWait();

        if (respuesta.get() == ButtonType.OK) {
            confirm = true;
        }

        return confirm;
    }
}
