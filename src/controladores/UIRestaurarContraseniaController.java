/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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
        LOGGER.info("SignIn Controlador: Estableciendo stage");

        stage = primaryStage;
    }

    /**
     * Método que inicializa el escenario y los componentes del Restaurar
     * Contraseña.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("SignIn Controlador: Iniciando stage");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Restauracion de contraseña");
        stage.setResizable(false);

        txtEmail.requestFocus();
        btnRestaurarContraseña.setDisable(true);
        btnVolver.setOnAction(this::handleVolver);

        stage.show();
    }


    private void handleVolver(ActionEvent event) {
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