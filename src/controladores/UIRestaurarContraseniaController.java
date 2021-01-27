/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidad.Usuario;
import excepcion.EmailNoExisteException;
import factorias.GestionFactoria;
import interfaces.UsuarioGestion;
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
import javafx.scene.control.TextField;
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
    private TextField txtEmail;
    @FXML
    private Button btnRestaurarContrasenia;
    @FXML
    private Button btnVolver;
    @FXML
    private Label lblEmailError;
    @FXML
    private Label lblContraseniaRestaurada;
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
        LOGGER.info("RestaurarContrasenia Controlador: Estableciendo stage");

        stage = primaryStage;
    }

    /**
     * Método que inicializa el escenario y los componentes del Restaurar
     * Contraseña.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("RestaurarContrasenia Controlador: Iniciando stage");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Restaurar contraseña");
        stage.setResizable(false);

        txtEmail.requestFocus();
        btnRestaurarContrasenia.setDisable(true);
        btnVolver.setOnAction(this::handleBotonVolver);

        txtEmail.textProperty().addListener(this::handleTextoCambiado);
        btnRestaurarContrasenia.setOnAction(this::handleBotonRestaurarContrasenia);

        stage.show();
    }

    /**
     * Método que envia un correo para restaurar la contraseña.
     *
     * @param event El evento de acción.
     */
    private void handleBotonRestaurarContrasenia(ActionEvent event) {
        try {
            LOGGER.info("RestaurarContrasenia Controlador: Restaurando contraseña");

            UsuarioGestion usuarioGestion = GestionFactoria.getUsuarioGestion();

            Usuario usuario=new Usuario();
            usuario.setEmail(txtEmail.getText());
            
            usuarioGestion.buscarUsuarioPorEmailContra(txtEmail.getText());
            usuarioGestion.buscarUsuarioParaEnviarMail(usuario);

            lblContraseniaRestaurada.setText("Contraseña restaurada. Revisa tu email");
            lblContraseniaRestaurada.setTextFill(Color.web("#008000"));
        } catch (EmailNoExisteException ene) {
            LOGGER.severe(ene.getMessage());
            lblEmailError.setText("Email no encontrado");
            lblEmailError.setTextFill(Color.web("#FF0000"));
        }
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
        boolean textoCorrecto = false;

        if (!txtEmail.getText().isEmpty()) {
            if (!EmailIsOver50()) {
                if (emailPatternIsCorrect()) {
                    textoCorrecto = true;
                }
            }
        }
        if (textoCorrecto) {
            btnRestaurarContrasenia.setDisable(false);
        } else {
            btnRestaurarContrasenia.setDisable(true);
        }
    }

    /**
     * Comprueba que el correo introducido sigue un patron aceptable de
     * direccion de correo.
     *
     * @return Variable indicando si el patron es correcto o no.
     */
    private boolean emailPatternIsCorrect() {
        boolean correct = true;

        Matcher matcher = VALID_EMAIL.matcher(txtEmail.getText());
        // Si el texto y el patrón no coinciden
        if (!matcher.find()) {
            lblEmailError.setText("Dirección de email invalida");
            lblEmailError.setTextFill(Color.web("#FF0000"));

            correct = false;
        } else {
            lblEmailError.setText("");
        }

        return correct;
    }

    /**
     * Comprueba que el texto introducido no supera los 50 caracteres. Si se
     * supera no muestra ni recoge los nuevos caracteres introducidos.
     *
     * @return Variable indicando si supera los 50 caracteres o no.
     */
    private boolean EmailIsOver50() {
        boolean textOver50 = false;

        if (txtEmail.getText().length() > MAX_LENGHT) {
            String text = txtEmail.getText().substring(0, MAX_LENGHT);
            txtEmail.setText(text);
            lblEmailError.setText("Email debe ser menor de 50 caracteres");
            lblEmailError.setTextFill(Color.web("#FF0000"));
            textOver50 = true;
        } else {
            lblEmailError.setText("");
        }

        return textOver50;
    }

    /**
     * Método que carga y abre la venta UISignIn.
     *
     * @param event El evento de acción.
     */
    private void handleBotonVolver(ActionEvent event) {
        LOGGER.info("RestaurarContrasenia Controlador: Iniciando vista SignIn");

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
