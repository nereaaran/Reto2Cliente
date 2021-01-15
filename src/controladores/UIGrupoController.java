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
public class UIGrupoController implements Initializable {

    @FXML
    private AnchorPane paneGeneralGrupo;
    @FXML
    private AnchorPane paneSuperiorGrupo;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Label lblNombreGrupo;
    @FXML
    private Label lblDescripcion;
    @FXML
    private TextField txtNombreGrupo;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Label lblGestionGrupos;
    @FXML
    private TextField txtBuscarGrupo;
    @FXML
    private Button btnAnadir;
    @FXML
    private Button btnGestionarAlumno;
    @FXML
    private Button btnBuscar;
    @FXML
    private MenuBar munuBar;
    @FXML
    private MenuItem mnMiPerfil;
    @FXML
    private MenuItem mnCerrarSesion;
    @FXML
    private Label lblNombreGrupoError;
    @FXML
    private Label lblBuscarGrupoError;
    @FXML
    private Label lblDescripcionError;
    @FXML
    private AnchorPane paneInferiorGrupo;
    @FXML
    private TableView<?> tablaLibro;
    @FXML
    private TableColumn<?, ?> nombreGrupoCL;
    @FXML
    private TableColumn<?, ?> descripcionCL;
    @FXML
    private TableColumn<?, ?> numAlumnosCL;
    @FXML
    private TableColumn<?, ?> nombreProfesorCL;
    @FXML
    private TableView<?> tablaLibro1;
    @FXML
    private TableColumn<?, ?> tituloCL;
    @FXML
    private TableColumn<?, ?> fechaInicioCL;
    @FXML
    private TableColumn<?, ?> fechaFinCL;
    @FXML
    private Label lblListaGrupos;
    @FXML
    private Label lblLibrosAsignados;
    @FXML
    private Button btnAnadirLibro;
    @FXML
    private Button btnEliminarLibro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}