/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nerea
 */
public class UIGrupoLibroController implements Initializable {

    @FXML
    private AnchorPane paneGeneralGrupoLibro;
    @FXML
    private AnchorPane paneSuperiorGrupoLibro;
    @FXML
    private Button btnBuscar;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblFechaInicio;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAutor;
    @FXML
    private Label lblAsignarLibros;
    @FXML
    private TextField txtBuscarLibro;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnAnadir;
    @FXML
    private Label lblAutor;
    @FXML
    private Label lblFechaLimite;
    @FXML
    private DatePicker datePickerFechaInico;
    @FXML
    private DatePicker datePickerFechaLimite;
    @FXML
    private MenuBar munuBar;
    @FXML
    private MenuItem mnMiPerfil;
    @FXML
    private MenuItem mnCerrarSesion;
    @FXML
    private Label lblTituloError;
    @FXML
    private Label lblFechaInicioError;
    @FXML
    private Label lblFechaLimiteError;
    @FXML
    private Label lblAutorError;
    @FXML
    private Label lblBuscarLibroError;
    @FXML
    private AnchorPane paneInferiorGrupoLibro;
    @FXML
    private TableView<?> tablaLibro;
    @FXML
    private TableColumn<?, ?> tituloCL;
    @FXML
    private TableColumn<?, ?> autorCL;
    @FXML
    private TableColumn<?, ?> editorialCL;
    @FXML
    private TableColumn<?, ?> generoCL;
    @FXML
    private TableColumn<?, ?> isbnCL;
    @FXML
    private TableColumn<?, ?> descargableCL;
    @FXML
    private TableColumn<?, ?> linkdescargableCL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void anadirLibro(ActionEvent event) {
    }
    
}
