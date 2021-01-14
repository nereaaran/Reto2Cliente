/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Controlador de la vista UIRestaurarContrasenia que contiene los metodos para
 * definir y controlar su comportamiento.
 *
 * @author Nerea Aranguren
 */
public class UIRestaurarContraseniaController {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("controladores.UIRestaurarContraseniaController");

    /**
     * Variable que guarda los carácteres máximos de los campos de texto.
     */
    private static final int MAX_LENGHT = 50;
    /**
     * Variable que guarda el patrón correcto del mail
     */
    public static final Pattern VALID_EMAIL = Pattern.compile("^[A-ZÀ-ÿ0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * Lista de elementos importados de la vista FXML que representan objetos.
     */
    @FXML
    private Pane paneRestaurarContrasenia;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblEmail;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnRestaurarContraseña;
    @FXML
    private Button btnVolver;
    @FXML
    private Label lblEmailError;
    @FXML
    private Label lblInformativo;
    @FXML
    private Label lblContraseñaRestaurada;
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
        LOGGER.info("RestaurarContraseña Controlador: Estableciendo stage");

        stage = primaryStage;
    }

    /**
     * Método que inicializa el escenario y los componentes del Restaurar
     * Contraseña.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("RestaurarContraseña Controlador: Iniciando stage");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Restaurar contraseña");
        stage.setResizable(false);

        txtEmail.requestFocus();
        btnRestaurarContraseña.setDisable(true);
        btnVolver.setOnAction(this::handleBotonVolver);

        txtEmail.textProperty().addListener(this::handleTextoCambiado);

        habilitarBotonRestaurarContrasena();

        stage.show();
    }

    private void habilitarBotonRestaurarContrasena() {
        if (txtEmail.getText().isEmpty() ||) {

            btnRestaurarContraseña.setDisable(true);
        } else {

            btnRestaurarContraseña.setDisable(false);
        }

    }

    private void handleTextoCambiado(ObservableValue observable, String oldValue, String newValue) {

        if (!textFieldOver50()) {

        }

    }

    private boolean textFieldOver50() {
        boolean over50 = false;
        // Si la longitud del texto es superior 50
        if (txtEmail.getText().length() > MAX_LENGHT) {
            // Guarda en un String los caracteres escritos en el campo de textodesde el 1 hasta el ultimo.
            String text = txtEmail.getText().substring(0, MAX_LENGHT);
            // Sustituye el texto por el String.
            txtEmail.setText(text);

            over50 = true;

            lblEmailError.setText("Name must be less than 50 characters");
            lblEmailError.setTextFill(Color.web("#FF0000"));
        } else {
            lblEmailError.setText("");
        }
        return over50;
    }

    /**
     * Método que carga y abre la venta UISignIn.
     *
     * @param event El evento de acción.
     */
    private void handleBotonVolver(ActionEvent event) {
        LOGGER.info("RestaurarContraseña Controlador: Iniciando vista SignIn");

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
