/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import entidad.Grupo;
import implementaciones.GrupoGestionImplementation;
import interfaces.GrupoGestion;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jonathan Viñan
 */
public class UIGrupoController {

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
    private TableView<?> tablaGrupo;
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
    
    private static final Logger LOGGER = Logger.getLogger(UIGrupoController.class.getName());
            
     ObservableList<Grupo> grupos;

    private void anadirGrupo(ActionEvent event){
        Grupo grupo = new Grupo();
        
        grupo.setNombre(txtNombreGrupo.getText());
        grupo.setDescripcion(txtDescripcion.getText());
        grupo.setNumAlumno(Integer.MIN_VALUE);
        grupos.add(grupo);
    }
    
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
        LOGGER.info("Grupo Controlador: Estableciendo stage");

        stage = primaryStage;
    }

    /**
     * Método que inicializa el escenario y los componentes del Login.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("Grupo Controlador: Iniciando stage");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion de grupos");
        stage.setResizable(false);
        
        
        txtNombreGrupo.requestFocus();
        //btnIniciarSesion.setDisable(true);
        //linkContraseñaOlvidada.setOnAction(this::handleContraseñaOlvidada);
        //btnIniciarSesion.setOnAction(this::handleIniciarSesion);
        
        
        
        stage.show();
    }
    
    public void handleGrupoInitialize(ActionEvent event) {
           
        GrupoGestion grupoGestion = new GrupoGestionImplementation();
        
        // TODO
    }
    
    
}
