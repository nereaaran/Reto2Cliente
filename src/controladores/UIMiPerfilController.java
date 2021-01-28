/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.Optional;
import factorias.GestionFactoria;
import interfaces.UsuarioGestion;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controlador de la vista UIMiPerfil que contiene los metodos para definir y
 * controlar su comportamiento.
 *
 * @author Nerea Aranguren
 */
public class UIMiPerfilController {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("controladores.UIMiPerfilController");

    /**
     * Variable que guarda los carácteres máximos de los campos de texto.
     */
    private static final int MAX_LENGHT = 50;
    /**
     * Variable que guarda el patrón correcto de la contraseña.
     */
    public static final Pattern VALID_CONTRASENA = Pattern.compile("^[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);

    /**
     * Lista de elementos importados de la vista FXML que representan objetos.
     */
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
     * Variable de tipo stage que se usa para visualizar la ventana.
     */
    private Stage stage;

    /**
     *
     * Crea un nuevo escenario para que cuando se vuelva de Mi Perfil no se
     * cierre la aplicacion.
     *
     */
    public void setStage() {
        LOGGER.info("MiPerfil Controlador: Estableciendo stage");

        stage = new Stage();
    }

    /**
     * Método que inicializa el escenario y los componentes de Mi Perfil.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("MiPerfil Controlador: Iniciando stage");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Mi perfil");
        stage.setResizable(false);

        stage.onCloseRequestProperty().set(this::cerrarVentana);

        txtNombreProfesor.setDisable(true);
        txtEmailProfesor.setDisable(true);
        txtUsuarioProfesor.setDisable(true);
        txtTelefonoProfesor.setDisable(true);

        btnCambiarContraseña.setDisable(true);

        txtContrasenaActual.requestFocus();

        txtContrasenaActual.textProperty().addListener(this::handleTextoCambiado);
        txtContrasenaNueva.textProperty().addListener(this::handleTextoCambiado);
        txtRepiteNuevaContrasena.textProperty().addListener(this::handleTextoCambiado);

        btnVolver.setOnAction(this::handleBotonVolver);
        btnCambiarContraseña.setOnAction(this::handleBotonCambiarContrasena);
        stage.show();
    }

    private void handleBotonCambiarContrasena(ActionEvent event) {
        LOGGER.info("MiPerfil Controlador: Pulsado boton Cambiar Contraseña");

        if (txtContrasenaNueva.getText().equals(txtRepiteNuevaContrasena.getText())) {

            UsuarioGestion usuarioGestion = GestionFactoria.getUsuarioGestion();

            //usuarioGestion.buscarEmailParaEnviarMailCambiarContrasenia(Tu email);

            lblContrasenaCambiada.setText("La contraseña se ha cambiado");
            lblContrasenaCambiada.setTextFill(Color.web("#008000"));

            txtContrasenaActual.clear();
            txtContrasenaNueva.clear();
            txtRepiteNuevaContrasena.clear();
            lblContraseñaNuevaRepetirError.setText("");
        } else {
            lblContrasenaCambiada.setText("");
            lblContraseñaNuevaRepetirError.setText("Las contraseñas no coinciden");
            lblContraseñaNuevaRepetirError.setTextFill(Color.web("#FF0000"));
        }
    }

    /**
     * Cierra la ventana MiPerfil.
     *
     * @param event El evento de acción.
     */
    private void handleBotonVolver(ActionEvent event) {
        LOGGER.info("MiPerfil Controlador: Cerrando MiPerfil");

        stage.close();
    }

    /**
     * Se ejecuta cuando un campo de texto ha sido editado. Comprueba que el
     * campo de texto no esta vacio, no supera los 50 caracteres y sigue el
     * patron establecido de correo. Si cumple los requisitos habilita el boton
     * de RestaurarContraseña, y si no los cumple lo hace lo deshabilita.
     *
     * @param observable El valor que se observa.
     * @param oldValue El valor antiguo del observable.
     * @param newValue El valor nuevo del observable.
     */
    private void handleTextoCambiado(ObservableValue observable, String oldValue, String newValue) {
        lblContraseñaNuevaRepetirError.setText("");

        // Identifica el TextField que lo ha llamado
        StringProperty textProperty = (StringProperty) observable;
        // Guarda el textField
        TextField changedTextField = (TextField) textProperty.getBean();

        if (!camposTextoVacios() && !textFieldOver50(changedTextField) && patronContrasenaCorrecta()) {
            btnCambiarContraseña.setDisable(false);
        } else {
            btnCambiarContraseña.setDisable(true);
        }
    }

    /**
     * Compara la contraseña con el patron establecido para indicar si lo cumple
     * o no.
     *
     * @return Variable que indica si cumple el patron o no.
     */
    private boolean patronContrasenaCorrecta() {
        Matcher matcher = VALID_CONTRASENA.matcher(txtContrasenaNueva.getText());
        if (!matcher.find()) {
            if (txtContrasenaNueva.getText().isEmpty()) {
                txtContrasenaNueva.setText("");
            } else {
                lblContraseñaNuevaError.setText("Introduce solo letras y números");
                lblContraseñaNuevaError.setTextFill(Color.web("#FF0000"));
            }
            return false;
        } else {
            lblContraseñaNuevaError.setText("");
            return true;
        }
    }

    /**
     * Comprueba si los campo de texto contienen texto o no.
     *
     * @return El valor informando si estan vacios o no.
     */
    private boolean camposTextoVacios() {
        return txtContrasenaActual.getText().isEmpty() || txtContrasenaNueva.getText().isEmpty() || txtRepiteNuevaContrasena.getText().isEmpty();
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
