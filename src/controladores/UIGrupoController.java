/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidad.Grupo;
import entidad.GrupoLibro;
import factorias.GestionFactoria;
import interfaces.GrupoGestion;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
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
import javax.ws.rs.ClientErrorException;

/**
 * FXML Controller class
 *
 * @author Jonathan Viñan
 */
public class UIGrupoController {

    /**
     * Variable que guarda los carácteres máximos del nombre del grupo.
     */
    private static final int MAX_LENGHT_NOMBRE_GRUPO = 100;
    /**
     * Variable que guarda los carácteres máximos de la descripción del grupo.
     */
    private static final int MAX_LENGHT_DESCRIPCION = 200;
    /**
     * Variable que guarda los carácteres máximos de la cantidad total.
     */
    private static final int MAX_LENGHT_CANTIDAD_TOTAL = 2;
    /**
     * Atributo estático y constante que guarda un patron alfanumerico.
     */
    public static final Pattern VALIDAR_ALFANUMERICO = Pattern.compile("^[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    /**
     * Atributo estático y constante que guarda un patron numerico.
     */
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

    GrupoGestion grupoGestion;
    ObservableList<Grupo> GrupoObservableList;
    ObservableList<GrupoLibro> GrupoLibroObservableList;

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

        tituloCL.setCellValueFactory(new PropertyValueFactory<>("libro"));
        fechaInicioCL.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        fechaFinCL.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        RellenarTabla();

        //Propiedades de cambio de texto
        txtNombreGrupo.textProperty().addListener(this::comprobarLongitud);
        txtDescripcion.textProperty().addListener(this::comprobarLongitud);

        //Propiedades al iniar los botones
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
        btnAnadir.setOnAction(this::btnAnadirGrupos);
        btnModificar.setOnAction(this::modificarGrupo);
        //btnEliminar.setOnAction(this::eliminarGrupo);
        btnLimpiar.setOnAction(this::limpiarCamposTexto);

        btnBuscar.setDisable(true);
        btnEliminarLibro.setDisable(true);
        btnGestionarAlumno.setOnAction(this::vistaGestionarAlumno);
        btnConsultarLibros.setOnAction(this::vistaVerLibros);

        stage.show();
    }

    /**
     * Cuando se selecciona una fila de la tabla se rellenan los campos de texto
     * con la informacion del grupo y se desactiva el boton "btnAñadir".
     *
     * @param observable El objeto siendo observado.
     * @param oldValue El valor viejo de la propiedad.
     * @param newValue El valor nuevo de la propiedad.
     */
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

    /**
     * Metodo que rellena en la tabla de datos del los grupos
     */
    private void RellenarTabla() {
        try {
            grupoGestion = GestionFactoria.getGrupoGestion();
            GrupoObservableList = FXCollections.observableArrayList(grupoGestion.listarGrupos());
            tablaGrupos.setItems(GrupoObservableList);
            tablaGrupos.getSelectionModel().selectedItemProperty().addListener(this::tablaGrupoSeleccionado);
            tablaGrupos.refresh();
        } catch (ClientErrorException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Se ejecuta cuando se pulsa el boton Añadir. Si los datos son correctos y
     * el grupo no existe se añade a la base de datos y se actualiza la tabla.
     *
     * @param event El evento de acción.
     */
    private void btnAnadirGrupos(ActionEvent event) {
        LOGGER.info("Alumno Controlador: Comprobando errores");

        boolean errorCampos = comprobarCampos();

        if (!errorCampos) {
            try {
                //Comprueba si existe el login
                LOGGER.info("Alumno Controlador: Comprobando si existe el login");

                grupoGestion.listarGrupoPorNombre(txtNombreGrupo.getText());

                //Se crea el grupo
                LOGGER.info("Alumno Controlador: Creando alumno");

                Grupo grupoNew = new Grupo();
                grupoNew.setNombre(txtNombreGrupo.getText());
                grupoNew.setDescripcion(txtDescripcion.getText());
                grupoNew.setProfesor(null);
                grupoNew.setAlumnos(null);
                grupoNew.setGrupoLibros(null);

                grupoGestion.create(grupoNew);

                tablaGrupos.getItems().add(grupoNew);
                RellenarTabla();

                //limpiarCamposTexto();
            } catch (Exception le) {
                LOGGER.severe(le.getMessage());
                lblNombreGrupoError.setText("El login ya existe");
                lblNombreGrupoError.setTextFill(Color.web("#FF0000"));
            }
        }
    }

    /**
     * Metodo para modificar los datas de un grupo existe
     */
    private void modificarGrupo(ActionEvent event) {

    }

    /**
     * Cuando se selecciona una fila de la tabla se rellenan los campos de texto
     * con la informacion del grupo ".
     *
     * @param observable El objeto siendo observado.
     * @param oldValue El valor viejo de la propiedad.
     * @param newValue El valor nuevo de la propiedad.
     */
    private void seleccionDatoTabla(ObservableValue observable, Object oldValue, Object newValue) {
        if (newValue != null) {
            Grupo grupo = (Grupo) newValue;
            txtNombreGrupo.setText(grupo.getNombre());
            txtDescripcion.setText(grupo.getDescripcion());
            btnAnadir.setDisable(true);
        }
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
            LOGGER.info("Grupo Controlador: Cerrando aplicacion");
            stage.close();
            Platform.exit();
        } else {
            event.consume();
            alert.close();
        }
    }

    /**
     * Cuando se pulse el boton de gestionar alumno se dirigira a la vista de
     * gestion de grupo
     *
     * @param event El evento de acción.
     */
    private void vistaGestionarAlumno(ActionEvent event) {
        try {
            LOGGER.info("Reto2Cliente: Iniciando pantalla gestionar Alumno");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UIAlumno.fxml"));
            Parent root = (Parent) loader.load();
            UIAlumnoController controlador = (UIAlumnoController) loader.getController();
            controlador.setStage(stage);
            controlador.initStage(root);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Cuando se pulse el boton de consultar libros se dirigira a la vista de
     * gestion de libros
     *
     * @param event El evento de acción.
     */
    private void vistaVerLibros(ActionEvent event) {
        try {
            LOGGER.info("Reto2Cliente: Iniciando pantalla gestionar Libro");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UILibro.fxml"));
            Parent root = (Parent) loader.load();
            UILibroController controlador = (UILibroController) loader.getController();
            controlador.setStage(stage);
            controlador.initStage(root);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Comprueba que los patrone de nombre_Grupo , descripcion total e isbn son
     * correctos.
     *
     * @return Variable indicando si todos los patrones son correctos.
     */
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

    /**
     * Se eliminar un grupo de la base de datos
     */
    private void eliminarGrupo() {
        Grupo SeleccionadoGrupo = ((Grupo) tablaGrupos.getSelectionModel().getSelectedItem());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar");
        alert.setHeaderText(null);
        alert.setResizable(false);
        alert.setContentText("¿Seguro que quieres eliminar este grupo?");

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK) {
            grupoGestion.remove(SeleccionadoGrupo.getIdGrupo());

            tablaGrupos.getItems().remove(SeleccionadoGrupo);
            tablaGrupos.refresh();

            limpiarCampos();
        }
    }

    /**
     * Quita en los campos txtNombreGrupo, txtDescripcion lo que esta escrito
     *
     * @param event El evento de acción.
     */
    private void limpiarCamposTexto(ActionEvent event) {
        txtNombreGrupo.setText("");
        txtDescripcion.setText("");
        txtBuscarGrupo.setText("");
    }

    /**
     * Método que comprueba la longitud de los campos de texto. Si exceden el
     * maximo permitido no recoge el resto de carácteres.
     *
     * @param observable El valor que se observa.
     * @param oldValue El valor antiguo del observable.
     * @param newValue El valor nuevo del observable.
     */
    private void comprobarLongitud(ObservableValue observable, String oldValue, String newValue) {
        if (txtNombreGrupo.getText().length() > MAX_LENGHT_NOMBRE_GRUPO) {
            String nombreCompleto = txtNombreGrupo.getText().substring(0, MAX_LENGHT_NOMBRE_GRUPO);
            txtNombreGrupo.setText(nombreCompleto);
        }

        if (txtDescripcion.getText().length() > MAX_LENGHT_DESCRIPCION) {
            String descripcion = txtDescripcion.getText().substring(0, MAX_LENGHT_DESCRIPCION);
            txtDescripcion.setText(descripcion);
        }

        StringProperty textProperty = (StringProperty) observable;
        TextField changedTextField = (TextField) textProperty.getBean();
        String changedTextFieldName = changedTextField.getId();

        if (changedTextFieldName.equals("txtBuscarGrupo") && newValue.isEmpty()) {
            GrupoObservableList = FXCollections.observableArrayList(grupoGestion.listarGrupos());
            tablaGrupos.setItems(GrupoObservableList);
        }

        habilitarBotones();
    }

    /**
     * Método que habilita o deshabilita los botones Añadir, Modificar y
     * Eliminar dependiendo si los campos están vacíos o no.
     */
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

            btnBuscar.setDisable(true);
        } else {
            btnBuscar.setDisable(false);
        }
    }

    /**
     * Se comprueba que los campos estan vacios
     *
     * @return un booleas
     */
    private boolean camposVacios() {
        boolean vacio = false;

        if (txtNombreGrupo.getText().isEmpty() || txtDescripcion.getText().isEmpty()) {
            vacio = true;
        }

        return vacio;
    }

    /**
     * Se comprueba que los campos estan no esta vacios
     *
     * @return un booleas
     */
    private boolean camposNoVacios() {
        boolean vacio = false;

        if (!txtNombreGrupo.getText().isEmpty() || !txtDescripcion.getText().isEmpty()) {
            vacio = true;
        }

        return vacio;
    }

    /**
     * Metodo q se utiliza para limpiar los datos despues de cada accion de los
     * boones btnanadir, btnmodificar
     */
    private void limpiarCampos() {
        txtNombreGrupo.setText("");
        txtDescripcion.setText("");
    }

}