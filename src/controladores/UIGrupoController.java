/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidad.Grupo;
import entidad.GrupoLibro;
import factorias.GestionFactoria;
import implementaciones.GrupoGestionImplementation;
import interfaces.GrupoGestion;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Jonathan Viñan
 */
public class UIGrupoController {

    private static final int MAX_LENGHT_NOMBRE_GRUPO = 100;
    private static final int MAX_LENGHT_DESCRIPCION = 200;
    private static final int MAX_LENGHT_CANTIDAD_TOTAL = 2;
    public static final Pattern VALIDAR_ALFANUMERICO = Pattern.compile("^[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALIDAR_ES_NUMERO = Pattern.compile("^[0-9]", Pattern.CASE_INSENSITIVE);

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
    private Button btnConsultarLibros;
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
    private TableView<Grupo> tablaGrupos;
    @FXML
     private TableColumn<Grupo, String> nombreGrupoCL;
    @FXML
    private TableColumn<Grupo, String> descripcionCL;
    @FXML
    private TableColumn<Grupo, Integer> numAlumnosCL;
    @FXML
    private TableView<GrupoLibro> tablaGrupoLibro;
    @FXML
    private TableColumn<GrupoLibro, String> tituloCL;
    @FXML
    private TableColumn<GrupoLibro, Date> fechaInicioCL;
    @FXML
    private TableColumn<GrupoLibro, Date> fechaFinCL;
    @FXML
    private Label lblListaGrupos;
    @FXML
    private Label lblLibrosAsignados;
    @FXML
    private Button btnAnadirLibro;
    @FXML
    private Button btnEliminarLibro;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;   

    //private GrupoGestionImplementation gestionImplementation;
     GrupoGestion grupoGestion ;
    ObservableList<Grupo> GrupoObservableList ;
    ObservableList<GrupoLibro> GrupoLibroObservableList;

    //ObservableList listaGrupos = FXCollections.observableArrayList();
    private static final Logger LOGGER = Logger.getLogger(UIGrupoController.class.getName());

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
        LOGGER.info("Grupo Controlador: Iniciando");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestion de grupos");
        stage.setResizable(false);
        stage.onCloseRequestProperty().set(this::cerrarVentana);
       
        //Iniciando las columnas de la tabla Grupo
        nombreGrupoCL.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcionCL.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        numAlumnosCL.setCellValueFactory(new PropertyValueFactory<>("numAlumno"));
        
        tituloCL.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        fechaInicioCL.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        fechaFinCL.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        
        grupoGestion=  GestionFactoria.getGrupoGestion();
        GrupoObservableList = FXCollections.observableArrayList(grupoGestion.listarGrupos());
        tablaGrupos.setItems(GrupoObservableList);
        tablaGrupos.getSelectionModel().selectedItemProperty().addListener(this::tablaGrupoSeleccionado);
       
        
        
        //grupoGestion = new GrupoLibroGestionImplementation();
        
        //Propiedades de cambio de texto
        txtNombreGrupo.textProperty().addListener(this::handleTextoCambiado);
        txtDescripcion.textProperty().addListener(this::handleTextoCambiado);

       

        txtNombreGrupo.requestFocus();
        btnAnadir.setDisable(false);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnLimpiar.setDisable(true);
        btnBuscar.setDisable(true);
        btnGestionarAlumno.setDisable(true);
        btnEliminarLibro.setDisable(true);
        btnGestionarAlumno.setDisable(false);
        btnConsultarLibros.setDisable(false);

        //Acciones de los botnes
        btnAnadir.setOnAction(this::anadirGrupos);
        btnModificar.setOnAction(this::modificarGrupo);
        //btnEliminar.setOnAction(this::eliminarGrupo);
        btnLimpiar.setDisable(true);
        btnBuscar.setDisable(true);
        btnGestionarAlumno.setDisable(true);
        btnEliminarLibro.setDisable(true);
        btnGestionarAlumno.setDisable(false);
        btnConsultarLibros.setDisable(false);

        stage.show();
    }
    
    
    private void tablaGrupoSeleccionado(ObservableValue observable, Object oldValue, Object newValue) {
        if (newValue != null) {
            Grupo grupo = (Grupo) newValue;
            txtNombreGrupo.setText(grupo.getNombre());
            txtDescripcion.setText(grupo.getDescripcion());
            btnAnadir.setDisable(true);
        } else {
            txtNombreGrupo.setText("");
            txtDescripcion.setText("");
            btnAnadir.setDisable(false);
        }
    }

    public GrupoGestionImplementation getGestionImplementation() {
        return this.getGestionImplementation();
    }

    private void anadirGrupos(ActionEvent event) {
        try {
            Grupo nuevoGrupo = new Grupo();
            nuevoGrupo.setNombre(txtNombreGrupo.getText());
            nuevoGrupo.setDescripcion(txtDescripcion.getText());
            nuevoGrupo.setNumAlumno(0);

           // grupoGestion.create(nuevoGrupo);
            tablaGrupos.getItems().add(nuevoGrupo);
            tablaGrupos.refresh();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private void modificarGrupo(ActionEvent event) {
        if (comprobarCampos()) {
            ///////////////////////////////////////////////
            limpiarCamposTexto();
        }
    }

    private void seleccionDatoTabla(ObservableValue observable, Object oldValue, Object newValue) {
        if (newValue != null) {
            Grupo grupo = (Grupo) newValue;
            txtNombreGrupo.setText(grupo.getNombre());
            txtDescripcion.setText(grupo.getDescripcion());
            btnAnadir.setDisable(true);
        }
    }

    private void cerrarVentana(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Salir");
        alert.setHeaderText(null);
        alert.setContentText("¿Seguro que quieres cerrar la ventana?");

        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get().equals(ButtonType.OK)) {
            LOGGER.info("Grupo Controlador: Cerrando aplicacion");
            stage.close();
            Platform.exit();
        } else {
            event.consume();
            alert.close();
        }
    }

    private void handleTextoCambiado(ObservableValue observable, String oldValue, String newValue) {
        StringProperty textProperty = (StringProperty) observable;
        TextField changedTextField = (TextField) textProperty.getBean();
        String changedTextFieldName = changedTextField.getId();

        textFieldOverMaxLength(changedTextField, changedTextFieldName);

        habilitarBotones();
    }

    private boolean comprobarCampos() {
        boolean error = false;

        if (txtNombreGrupo.getText().isEmpty()) {
            lblNombreGrupoError.setText("");
        } else {
            Matcher matcher = VALIDAR_ALFANUMERICO.matcher(txtNombreGrupo.getText());
            if (!matcher.find()) {
                lblNombreGrupoError.setText("El nombre sólo debe contener alfanumericos");
                lblNombreGrupoError.setTextFill(Color.web("#FF0000"));

                error = true;
            } else {
                lblNombreGrupoError.setText("");
            }
        }

        if (txtDescripcion.getText().isEmpty()) {
            lblDescripcionError.setText("");
        } else {
            Matcher matcher = VALIDAR_ALFANUMERICO.matcher(txtDescripcion.getText());
            if (!matcher.find()) {
                lblDescripcionError.setText("Descripcion debe contener alfanumericos");
                lblDescripcionError.setTextFill(Color.web("#FF0000"));

                error = true;
            } else {
                lblDescripcionError.setText("");
            }
        }

        return error;

    }

    /*private void eliminarGrupo() {
        Grupo SeleccionadoGrupo = ((Grupo) tablaGrupo.getSelectionModel().getSelectedItem());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar");
        alert.setHeaderText(null);
        alert.setResizable(false);
        alert.setContentText("¿Seguro que quieres eliminar este grupo?");

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if (button == ButtonType.OK) {
            grupoGestion.remove(SeleccionadoGrupo);

            tablaGrupo.getItems().remove(SeleccionadoGrupo);
            tablaGrupo.refresh();

            limpiarCamposTexto();
        }
    } */

    private void limpiarCamposTexto() {
        txtNombreGrupo.setText("");
        txtDescripcion.setText("");
    }

    private void textFieldOverMaxLength(TextField changedTextField, String changedTextFieldName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void habilitarBotones() {
        if (camposVacios()) {
            btnAnadir.setDisable(true);
            btnModificar.setDisable(true);
            btnEliminar.setDisable(true);
        } else {
            btnAnadir.setDisable(false);
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);
        }

        if (camposNoVacios()) {
            btnLimpiar.setDisable(false);
        } else {
            btnLimpiar.setDisable(true);
        }

        if (txtBuscarGrupo.getText().isEmpty()) {
         //   listarGrupos = FXCollections.observableArrayList(grupoGestion.listarGrupos());
          //  tablaGrupo.setItems(listarGrupos);
            btnBuscar.setDisable(true);
        } else {
            btnBuscar.setDisable(false);
        }
    }

    private boolean camposVacios() {
        boolean vacio = false;

        if (txtNombreGrupo.getText().isEmpty() || txtDescripcion.getText().isEmpty() || txtBuscarGrupo.getText().isEmpty()) {
            vacio = true;
        }

        return vacio;
    }

    private boolean camposNoVacios() {
        boolean vacio = false;

        if (!txtNombreGrupo.getText().isEmpty() || !txtDescripcion.getText().isEmpty() || !txtBuscarGrupo.getText().isEmpty()) {
            vacio = true;
        }

        return vacio;
    }

}
