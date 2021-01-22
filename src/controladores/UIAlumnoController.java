/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidad.*;
import factorias.GestionFactoria;
import interfaces.*;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
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
 * Controlador de la vista UIAlumno que contiene los metodos para definir y
 * controlar su comportamiento.
 *
 * @author Cristina Milea
 */
public class UIAlumnoController {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    public static final Logger LOGGER = Logger.getLogger("controladores.UIAlumnoController");

    /**
     * Atributo estático y constante que guarda los caracteres máximos admitidos
     * en los campos de texto.
     */
    private static final int MAX_LENGHT = 50;
    /**
     * Atributo estático y constante que guarda los caracteres máximos admitidos
     * en el campo de texto de teléfono.
     */
    private static final int MAX_LENGHT_DNI = 9;

    /**
     * Atributo estático y constante que guarda el patron correcto del nombre.
     */
    public static final Pattern VALID_NOMBRE = Pattern.compile("^[A-Z\\s]+$", Pattern.CASE_INSENSITIVE);
    /**
     * Atributo estático y constante que guarda el patron correcto del DNI.
     */
    public static final Pattern VALID_DNI = Pattern.compile("^[XYZ]{0,1}[0-9]{7,8}[A-Z]", Pattern.CASE_INSENSITIVE);
    /**
     * Atributo estático y constante que guarda el patron correcto del usuario.
     */
    public static final Pattern VALID_USUARIO = Pattern.compile("^[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    /**
     * Atributo estático y constante que guarda el patron correcto del email.
     */
    public static final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * Elemento tipo anchorpane importado de la vista FXML.
     */
    @FXML
    private AnchorPane paneGeneralAlumno;
    /**
     * Elemento tipo anchorpane importado de la vista FXML.
     */
    @FXML
    private AnchorPane paneSuperiorAlumno;
    /**
     * Elemento tipo botón importado de la vista FXML.
     */
    @FXML
    private Button btnAnadir;
    /**
     * Elemento tipo botón importado de la vista FXML.
     */
    @FXML
    private Button btnModificar;
    /**
     * Elemento tipo botón importado de la vista FXML.
     */
    @FXML
    private Button btnEliminar;
    /**
     * Elemento tipo botón importado de la vista FXML.
     */
    @FXML
    private Button btnLimpiar;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblDni;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblUsuario;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblNombreCompleto;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblEmail;
    /**
     * Elemento tipo textfield importado de la vista FXML.
     */
    @FXML
    private TextField txtDni;
    /**
     * Elemento tipo textfield importado de la vista FXML.
     */
    @FXML
    private TextField txtUsuario;
    /**
     * Elemento tipo textfield importado de la vista FXML.
     */
    @FXML
    private TextField txtNombreCompleto;
    /**
     * Elemento tipo textfield importado de la vista FXML.
     */
    @FXML
    private TextField txtEmail;
    /**
     * Elemento tipo textfield importado de la vista FXML.
     */
    @FXML
    private TextField txtBuscarAlumno;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblFechaNacimiento;
    /**
     * Elemento tipo datepicker importado de la vista FXML.
     */
    @FXML
    private DatePicker datePickerFechaNacimiento;
    /**
     * Elemento tipo menubutton importado de la vista FXML.
     */
    @FXML
    private MenuButton menuGrupos;
    /**
     * Elemento tipo menuitem importado de la vista FXML.
     */
    @FXML
    private MenuItem lbNombreGrupo;
    /**
     * Elemento tipo checkbox importado de la vista FXML.
     */
    @FXML
    private CheckBox cbxNomberGrupo;
    /**
     * Elemento tipo botón importado de la vista FXML.
     */
    @FXML
    private Button btnVolver;
    /**
     * Elemento tipo botón importado de la vista FXML.
     */
    @FXML
    private Button btnBuscar;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblNombreCompletoError;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblDniError;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblUsuarioError;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblEmailError;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblFechaNacimientoError;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblBuscarAlumnoError;
    /**
     * Elemento tipo anchorpane importado de la vista FXML.
     */
    @FXML
    private AnchorPane paneInferiorAlumno;
    /**
     * Elemento tipo tabla importado de la vista FXML.
     */
    @FXML
    private TableView<Alumno> tablaAlumnos;
    /**
     * Elemento tipo tablecolumn importado de la vista FXML.
     */
    @FXML
    private TableColumn<Alumno, String> dniCL;
    /**
     * Elemento tipo tablecolumn importado de la vista FXML.
     */
    @FXML
    private TableColumn<Alumno, String> nombreCompletoCL;
    /**
     * Elemento tipo tablecolumn importado de la vista FXML.
     */
    @FXML
    private TableColumn<Alumno, Date> fechaNacimientoCL;
    /**
     * Elemento tipo tablecolumn importado de la vista FXML.
     */
    @FXML
    private TableColumn<Alumno, String> emailCL;
    /**
     * Elemento tipo tablecolumn importado de la vista FXML.
     */
    @FXML
    private TableView<Grupo> tablaGrupos;
    /**
     * Elemento tipo tabla importado de la vista FXML.
     */
    @FXML
    private TableColumn<Grupo, String> nombreGrupoCL;
    /**
     * Elemento tipo tablecolumn importado de la vista FXML.
     */
    @FXML
    private TableColumn<Grupo, Integer> numeroAlumnosCL;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblListaGrupos;
    /**
     * Elemento tipo label importado de la vista FXML.
     */
    @FXML
    private Label lblAlumnosAsignados;

    /**
     * Entidad Usuario del lado cliente.
     */
    private ObservableList<Alumno> alumnos;

    /**
     * Variable de tipo stage que se usa para visualizar la ventana.
     */
    private Stage stage;

    /**
     * Método que establece el escenario como escenario principal.
     *
     * @param primaryStage El escenario de Sign Up.
     */
    public void setStage(Stage primaryStage) {
        LOGGER.info("Alumno Controlador: Estableciendo stage");

        stage = primaryStage;
    }

    /**
     * Método que inicializa el escenario y los componentes de la vista.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("Alumno Controlador: Iniciando stage");

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Gestion de alumnos");
        stage.setResizable(false);

        nombreGrupoCL.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        numeroAlumnosCL.setCellValueFactory(new PropertyValueFactory<>("numAlumno"));

        //Falta lo de jony
        dniCL.setCellValueFactory(new PropertyValueFactory<>("dni"));
        nombreCompletoCL.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        fechaNacimientoCL.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        emailCL.setCellValueFactory(new PropertyValueFactory<>("email"));

        AlumnoGestion usuarioGestion = GestionFactoria.getAlumnoGestion();
        alumnos = FXCollections.observableArrayList(usuarioGestion.buscarTodosLosAlumnos());
        tablaAlumnos.setItems(alumnos);

        stage.onCloseRequestProperty().set(this::cerrarVentana);

        btnAnadir.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnBuscar.setDisable(true);
        btnLimpiar.setDisable(true);

        txtNombreCompleto.textProperty().addListener(this::comprobarLongitud);
        txtDni.textProperty().addListener(this::comprobarLongitud);
        txtUsuario.textProperty().addListener(this::comprobarLongitud);
        txtEmail.textProperty().addListener(this::comprobarLongitud);
        txtBuscarAlumno.textProperty().addListener(this::comprobarLongitud);

        btnAnadir.setOnAction(this::botonAnadirPulsado);
        btnModificar.setOnAction(this::botonModificarPulsado);
        btnEliminar.setOnAction(this::botonEliminarPulsado);
        btnLimpiar.setOnAction(this::botonLimpiarPulsado);
        btnBuscar.setOnAction(this::botonBuscarPulsado);

        btnVolver.setOnAction(this::botonVolverPulsado);

        stage.onCloseRequestProperty().set(this::cerrarVentana);

        stage.show();

        txtNombreCompleto.requestFocus();
    }

    /**
     * Método que comprueba que si hay algún campo de texto vacio.
     *
     * @return Variable que indica si hay algún campo de texto vacío.
     */
    private boolean camposVacios() {
        boolean vacio = false;

        if (txtNombreCompleto.getText().isEmpty() || txtDni.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            vacio = true;
        }

        return vacio;
    }

    /**
     * Método que comprueba que si hay algún campo de texto que no esté vacío.
     *
     * @return Variable que indica si hay algún campo de texto que no esté
     * vacío.
     */
    private boolean camposNoVacios() {
        boolean vacio = false;

        if (!txtNombreCompleto.getText().isEmpty() || !txtDni.getText().isEmpty() || !txtUsuario.getText().isEmpty() || !txtEmail.getText().isEmpty()) {
            vacio = true;
        }

        return vacio;
    }

    /**
     * Método que comprueba que si el datepicker está vacío.
     *
     * @return Variable que indica si el datepicker está vacío.
     */
    private boolean datePickerVacio() {
        boolean vacio = false;

        if (datePickerFechaNacimiento.getValue() == null) {
            lblFechaNacimientoError.setText("Tienes que introducir una fecha");
            lblFechaNacimientoError.setTextFill(Color.web("#FF0000"));

            vacio = true;
        } else {
            lblFechaNacimientoError.setText("");
        }

        return vacio;
    }

    /**
     * Método que habilita y deshabilita o habilita los botones Añadir,
     * Modificar y Eliminar.
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

        if (txtBuscarAlumno.getText().isEmpty()) {
            btnBuscar.setDisable(true);
        } else {
            btnBuscar.setDisable(false);
        }
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
        if (txtNombreCompleto.getText().length() > MAX_LENGHT) {
            String nombreCompleto = txtNombreCompleto.getText().substring(0, MAX_LENGHT);
            txtNombreCompleto.setText(nombreCompleto);
        }

        if (txtDni.getText().length() > MAX_LENGHT_DNI) {
            String dni = txtDni.getText().substring(0, MAX_LENGHT_DNI);
            txtDni.setText(dni);
        }

        if (txtUsuario.getText().length() > MAX_LENGHT) {
            String usuario = txtUsuario.getText().substring(0, MAX_LENGHT);
            txtUsuario.setText(usuario);
        }

        if (txtEmail.getText().length() > MAX_LENGHT) {
            String email = txtEmail.getText().substring(0, MAX_LENGHT);
            txtEmail.setText(email);
        }

        if (txtBuscarAlumno.getText().length() > MAX_LENGHT) {
            String buscar = txtBuscarAlumno.getText().substring(0, MAX_LENGHT);
            txtBuscarAlumno.setText(buscar);
        }

        habilitarBotones();
    }

    /**
     * Método que comprueba que el texto introducido en los campos de texto de
     * Nombre Completo, DNI, Usuario e Email cumple con los patrones
     * establecidos, si no lo hace muestra el error en los labels
     * correspondientes.
     *
     * @return Variable que indica si hay algún patrón que no se cumple.
     */
    private boolean comprobarPatrones() {
        boolean error = false;

        if (txtNombreCompleto.getText().isEmpty()) {
            lblNombreCompletoError.setText("");
        } else {
            Matcher matcher = VALID_NOMBRE.matcher(txtNombreCompleto.getText());
            if (!matcher.find()) {
                lblNombreCompletoError.setText("El nombre sólo debe contener letras");
                lblNombreCompletoError.setTextFill(Color.web("#FF0000"));

                error = true;
            } else {
                lblNombreCompletoError.setText("");
            }
        }

        if (txtDni.getText().isEmpty()) {
            lblDniError.setText("");
        } else {
            Matcher matcher = VALID_DNI.matcher(txtDni.getText());
            if (!matcher.find()) {
                lblDniError.setText("DNI inválido");
                lblDniError.setTextFill(Color.web("#FF0000"));

                error = true;
            } else {
                lblDniError.setText("");
            }
        }

        if (txtUsuario.getText().isEmpty()) {
            lblUsuarioError.setText("");
        } else {
            Matcher matcher = VALID_USUARIO.matcher(txtUsuario.getText());
            if (!matcher.find()) {
                lblUsuarioError.setText("El usuario sólo debe contener letras y números");
                lblUsuarioError.setTextFill(Color.web("#FF0000"));

                error = true;
            } else {
                lblUsuarioError.setText("");
            }
        }

        if (txtEmail.getText().isEmpty()) {
            lblEmailError.setText("");
        } else {
            Matcher matcher = VALID_EMAIL.matcher(txtEmail.getText());
            if (!matcher.find()) {
                lblEmailError.setText("Email inválido");
                lblEmailError.setTextFill(Color.web("#FF0000"));

                error = true;
            } else {
                lblEmailError.setText("");
            }
        }

        return error;
    }

    /**
     * Método que añade un alumno nuevo a la tabla.
     *
     * @param event El evento de acción.
     */
    private void botonAnadirPulsado(ActionEvent event) {
        LOGGER.info("Alumno Controlador: Comprobando errores");

        boolean errorPatrones = comprobarPatrones();
        boolean errorDatePicker = datePickerVacio();

        if (!errorPatrones || !errorDatePicker) {
            LOGGER.info("Alumno Controlador: Añadiendo alumno a la base de datos");

            //CREATE
        }
    }

    /**
     * Método que modifica un alumno de la tabla.
     *
     * @param event El evento de acción.
     */
    private void botonModificarPulsado(ActionEvent event) {
        boolean errorPatrones = comprobarPatrones();
        boolean errorDatePicker = datePickerVacio();

        if (!errorPatrones || !errorDatePicker) {
            lblBuscarAlumnoError.setText("OK"); //Quitar mas tarde
        }
    }

    /**
     * Método que elimina un alumno de la tabla.
     *
     * @param event El evento de acción.
     */
    private void botonEliminarPulsado(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar");
        alert.setHeaderText(null);
        alert.setResizable(false);
        alert.setContentText("¿Seguro que quieres eliminar el alumno?");

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if (button == ButtonType.OK) {
            //Eliminar alumno
            limpiarCampos();
        }
    }

    /**
     * Método que vacía los campos de texto y labels de error.
     *
     * @param event El evento de acción.
     */
    private void botonLimpiarPulsado(ActionEvent event) {
        limpiarCampos();
    }

    /**
     * Método que busca un alumno en la tabla.
     *
     * @param event El evento de acción.
     */
    private void botonBuscarPulsado(ActionEvent event) {
    }

    /**
     * Método que carga y abre la ventana UIGrupo.
     *
     * @param event El evento de acción.
     */
    private void botonVolverPulsado(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UIGrupo.fxml"));
            Parent root = (Parent) loader.load();
            UIGrupoController controlador = (UIGrupoController) loader.getController();
            controlador.setStage(stage);
            controlador.initStage(root);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Método que vacía los campos de texto y labels de error.
     *
     * @param event El evento de acción.
     */
    private void limpiarCampos() {
        txtNombreCompleto.clear();
        txtDni.clear();
        txtUsuario.clear();
        txtEmail.clear();
        datePickerFechaNacimiento.getEditor().clear();
        txtBuscarAlumno.clear();

        lblNombreCompletoError.setText("");
        lblDniError.setText("");
        lblUsuarioError.setText("");
        lblEmailError.setText("");
        lblFechaNacimientoError.setText("");
        lblBuscarAlumnoError.setText("");

        btnLimpiar.setDisable(true);

        txtNombreCompleto.requestFocus();
    }

    /**
     * Cuadro de diálogo que se abre al pulsar la "X" de la pantalla para
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
