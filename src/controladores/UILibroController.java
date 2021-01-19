/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controlador de la vista UILibro que contiene los metodos para definir y
 * controlar su comportamiento.
 *
 * @author Nerea Aranguren
 */
public class UILibroController {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("controladores.UILibroController");

    /**
     * Lista de elementos importados de la vista FXML que representan objetos.
     */
    @FXML
    private AnchorPane paneGeneralLibro;
    @FXML
    private AnchorPane paneSuperiorLibro;
    @FXML
    private Button btnAnadir;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Label lbIsbn;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblAutor;
    @FXML
    private Label lblEditorial;
    @FXML
    private Label lblGenero;
    @FXML
    private Label lblCantidadTotal;
    @FXML
    private Label lblDescargable;
    @FXML
    private TextField txtIsbn;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAutor;
    @FXML
    private TextField txtEditorial;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtCantidadTotal;
    @FXML
    private TextField txtLinkDescarga;
    @FXML
    private Label lblLinkDescarga;
    @FXML
    private CheckBox cbxDescargable;
    @FXML
    private Label lblGestionLibros;
    @FXML
    private TextField txtBuscarLibro;
    @FXML
    private Button btnBuscar;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem mnCerrarSesion;
    @FXML
    private Label lblTituloError;
    @FXML
    private Label lblAutorError;
    @FXML
    private Label lblGeneroError;
    @FXML
    private Label lblEditorialError;
    @FXML
    private Label lblCantidadTotalError;
    @FXML
    private Label lblIsbnError;
    @FXML
    private Label lblBuscarLibroError;
    @FXML
    private AnchorPane paneInferiorLibro;
    @FXML
    private TableView<?> tablaLibro;
    @FXML
    private TableColumn<?, ?> tituloCL;
    @FXML
    private TableColumn<?, ?> autorCL;
    @FXML
    private TableColumn<?, ?> editorialCL;
    @FXML
    private TableColumn<?, ?> isbnCL;
    @FXML
    private TableColumn<?, ?> generoCL;
    @FXML
    private TableColumn<?, ?> cantidadTotalCL;
    @FXML
    private TableColumn<?, ?> cantidadDisponibleCL;
    @FXML
    private TableColumn<?, ?> descargableCL;
    @FXML
    private TableColumn<?, ?> linkdescargaCL;
    /**
     * Variable de tipo stage que se usa para visualizar la ventana
     */
    private Stage stage;

    /**
     * Método que establece al escenario del UILibro como escenario principal.
     *
     * @param primaryStage El escenario principal.
     */
    public void setStage(Stage primaryStage) {
        LOGGER.info("Libro Controlador: Estableciendo stage");

        stage = primaryStage;
    }

    /**
     * Método que inicializa el escenario y los componentes de UILibro.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("Libro Controlador: Iniciando stage principal");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestión de libros");
        stage.setResizable(false);

        stage.onCloseRequestProperty().set(this::cerrarVentana);

        btnAnadir.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnLimpiar.setDisable(true);
        btnBuscar.setDisable(true);

        
        txtBuscarLibro.textProperty().addListener(this::handleTextoCambiado);
        
        
        txtTitulo.requestFocus();
        
        stage.show();
    }
    
    
    private void handleTextoCambiado(ObservableValue observable, String oldValue, String newValue){
        
        habilitarBotones();
        
    }
    
    
    private void habilitarBotones() {
    
    
    
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
