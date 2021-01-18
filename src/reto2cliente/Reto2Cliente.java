/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto2cliente;

import controladores.UISignInController;
import entidad.Libro;
import implementaciones.LibroGestionImplementation;
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
     * Método que inicia la Aplicación JavaFX. Carga y muestra la pantalla
     * inicial.
     *
     * @param primaryStage La pantalla principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            LOGGER.info("Reto2Cliente: Iniciando pantalla principal");

            // Carga el archivo fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UISignIn.fxml"));
            Parent ventana = (Parent) loader.load();
            // Enlaza el controlador con el archivo fxml
            UISignInController controlador = (UISignInController) loader.getController();
            // Prepara el escenario principal donde se ejecutara la ventana 
            controlador.setStage(primaryStage);
            // Inicializa la ventana de SignIn
            controlador.initStage(ventana);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LibroGestionImplementation lgi = new LibroGestionImplementation();
        
        Libro libro = new Libro();
        
        /*     CREATE LIBRO.    FUNCIONA. 
        libro.setTitulo("A");
        libro.setAutor("A");
        libro.setEditorial("A");
        libro.setIsbn(new Long(1));
        libro.setGenero("A");
        libro.setCantidadTotal(1);
        libro.setCantidadDisponible(1);
        libro.setDescargable(false);
        libro.setLinkDescarga("");
        lgi.create(libro);*/
        
        //     FIND LIBRO.     PERFECTO
        libro = lgi.find(1);
        //System.out.println(libro.toString());
        
        //      REMOVE LIBRO   SUPONGO PERFECTO
        //lgi.remove(libro);
        
        
        /*      EDIT LIBRO     SUPONGO PERFECTO
        libro.setAutor("MACAULY CULKIN");
        lgi.edit(libro);*/
        
        
        launch(args);
    }
    
}
