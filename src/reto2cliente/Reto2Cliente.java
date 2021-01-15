/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto2cliente;

import controladores.*;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author nerea
 */
public class Reto2Cliente extends Application {

    public static final Logger LOGGER = Logger.getLogger("reto2cliente.Reto2Cliente");

    @Override
    public void start(Stage primaryStage) {
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UISignUp.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UIAlumno.fxml"));
            Parent ventana = (Parent) loader.load();
            UIAlumnoController controlador = (UIAlumnoController) loader.getController();
            //UISignUpController controlador = (UISignUpController) loader.getController();
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
