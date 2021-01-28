/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto2cliente;

import controladores.UISignInController;
import entidad.Profesor;
import implementaciones.ProfesorGestionImplementation;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Clase principal del lado Cliente que inicia la Aplicacion JavaFX.
 *
 * @author Nerea Aranguren
 */
public class Reto2Cliente extends Application {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("reto2Cliente.Reto2Cliente");

    /**
     * Método que inicia la Aplicación JavaFX, carga y muestra la pantalla
     * inicial.
     *
     * @param primaryStage La pantalla principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            LOGGER.info("Reto2Cliente: Iniciando pantalla principal");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UISignIn.fxml"));
            Parent ventana = (Parent) loader.load();
            UISignInController controlador = (UISignInController) loader.getController();
            controlador.setStage(primaryStage);
            controlador.initStage(ventana);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        launch(args);
    }
}
