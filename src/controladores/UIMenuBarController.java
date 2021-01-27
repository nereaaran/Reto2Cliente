/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Controlador FXML para la vista de la barra de menu.
 *
 * @author Kristina Milea and Nerea Aranguren
 */
public class UIMenuBarController {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("controladores.UIMenuBarController");

    /**
     * Lista de elementos importados de la vista FXML que representan objetos.
     */
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem mbMiPerfil;
    @FXML
    private MenuItem mbCerrarSesion;

    /**
     * Variable de tipo stage que se usa para visualizar la ventana
     */
    private Stage stage;

    /**
     * Método que establece el escenario del menu bar.
     *
     * @param primaryStage El escenario principal.
     */
    public void setStage(Stage primaryStage) {
        LOGGER.info("UIMenuBarControlador: Estableciendo stage");

        stage = primaryStage;
    }

    /**
     * Método que inicializa el escenario y los componentes de UIMenuBar.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("UIMenuBarControlador: Iniciando stage");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu principal");
        stage.setResizable(false);
    }

    /**
     * Cuando se pulsa el menu item "Mi Perfil" se abre la vista "UIMiPerfil".
     *
     * @param event El evento de acción.
     */
    @FXML
    private void handleMiPerfilPresionado(ActionEvent event) {
        LOGGER.info("UIMenuBarControlador: Iniciando vista MiPerfil");

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
     * Cuando se pulsa el menu item "Cerrar sesion" se abre ua alerta
     * preguntando si se quiere cerrar. En caso afirmativo se abre la vista de
     * SignIn.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void handleCerrarSesionPresionado(ActionEvent event) {
        if (cerrarSesionVentana()) {
            LOGGER.info("UIMenuBarControlador: Iniciando vista SignIn");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UISignIn.fxml"));
                Parent root = (Parent) loader.load();
                UISignInController controller = ((UISignInController) loader.getController());
                controller.setStage(new Stage());
                controller.initStage(root);
                stage.close();
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
    private boolean cerrarSesionVentana() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Cerrar Sesion");
        alert.setHeaderText(null);
        alert.setContentText("¿Seguro que quieres cerrar sesion?");

        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get().equals(ButtonType.OK)) {
            return true;
        } else {
            alert.close();
            return false;
        }
    }

}