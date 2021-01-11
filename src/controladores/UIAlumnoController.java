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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
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
public class UIAlumnoController implements Initializable {

    @FXML
    private AnchorPane paneGeneralAlumno;
    @FXML
    private AnchorPane paneSuperiorAlumno;
    @FXML
    private Button btnAnadir;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Label lblDni;
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblNombreCompleto;
    @FXML
    private Label lblEmail;
    @FXML
    private TextField txtDni;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtNombreCompleto;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtBuscarAlumno;
    @FXML
    private Label lblFechaNacimiento;
    @FXML
    private DatePicker datePickerFechaNacimiento;
    @FXML
    private MenuButton menuGrupos;
    @FXML
    private MenuItem lbNombreGrupo;
    @FXML
    private CheckBox cbxNomberGrupo;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnBuscar;
    @FXML
    private MenuBar munuBar;
    @FXML
    private MenuItem mnMiPerfil;
    @FXML
    private MenuItem mnCerrarSesion;
    @FXML
    private Label lblNombreCompletoError;
    @FXML
    private Label lblDniError;
    @FXML
    private Label lblUsuarioError;
    @FXML
    private Label lblEmailError;
    @FXML
    private Label lblFechaNacimientoError;
    @FXML
    private Label lblBuscarAlumnoError;
    @FXML
    private AnchorPane paneInferiorAlumno;
    @FXML
    private TableView<?> tablaAlumno;
    @FXML
    private TableColumn<?, ?> dniCL;
    @FXML
    private TableColumn<?, ?> nombreCompletoCL;
    @FXML
    private TableColumn<?, ?> fechaNacimientoCL;
    @FXML
    private TableColumn<?, ?> emailCL;
    @FXML
    private TableView<?> tablaGrupo;
    @FXML
    private TableColumn<?, ?> nombreGrupoCL;
    @FXML
    private TableColumn<?, ?> numeroAlumnosCL;
    @FXML
    private Label lblListaGrupos;
    @FXML
    private Label lblAlumnosAsignados;

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

    @FXML
    private void modificarLibro(ActionEvent event) {
    }

    @FXML
    private void eliminarLibro(ActionEvent event) {
    }

    @FXML
    private void limpiarDatos(ActionEvent event) {
    }
    
}
