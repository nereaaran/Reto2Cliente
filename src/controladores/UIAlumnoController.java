/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cristina Milea
 */
public class UIAlumnoController {
    
    public static final Logger LOGGER = Logger.getLogger("controladores.UIAlumnoController");
    
    private static final int MAX_LENGHT = 50;
    private static final int MAX_LENGHT_DNI = 9;
    
    public static final Pattern VALID_NOMBRE = Pattern.compile("^[A-Z\\s]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_DNI = Pattern.compile("^[XYZ]{0,1}[0-9]{7,8}[A-Z]", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_USUARIO = Pattern.compile("^[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    private Stage stage;
    
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
    private TableView<?> tablaAlumnos;
    @FXML
    private TableColumn<?, ?> dniCL;
    @FXML
    private TableColumn<?, ?> nombreCompletoCL;
    @FXML
    private TableColumn<?, ?> fechaNacimientoCL;
    @FXML
    private TableColumn<?, ?> emailCL;
    @FXML
    private TableView<?> tablaGrupos;
    @FXML
    private TableColumn<?, ?> nombreGrupoCL;
    @FXML
    private TableColumn<?, ?> numeroAlumnosCL;
    @FXML
    private Label lblListaGrupos;
    @FXML
    private Label lblAlumnosAsignados;
    
    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
    
    public void initStage(Parent ventana) {
        Scene scene = new Scene(ventana);
        stage.setScene(scene);
        
        stage.setTitle("Gestion de alumnos");
        stage.setResizable(false);
        txtNombreCompleto.requestFocus();
        
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
        
        stage.show();
    }
    
    private boolean camposVacios() {
        boolean vacio = false;
        
        if (txtNombreCompleto.getText().isEmpty() || txtDni.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            btnLimpiar.setDisable(false);
            vacio = true;
        }
        
        return vacio;
    }
    
    private boolean datePickerVacio() {
        boolean vacio = false;
        
        if (datePickerFechaNacimiento.getValue() == null) { //esta mierda no funciona-----------------------------------
            lblFechaNacimientoError.setText("Vacio");
            vacio = true;
        } else {
            lblFechaNacimientoError.setText("No vacio");
        }
        
        return vacio;
    }
    
    private void habilitarBotones() {
        if (camposVacios() /*|| datePickerVacio()*/) {
            btnAnadir.setDisable(true);
            btnModificar.setDisable(true);
            btnEliminar.setDisable(true);
        } else {
            btnAnadir.setDisable(false);
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);
        }
        
        if (txtBuscarAlumno.getText().isEmpty()) {
            btnBuscar.setDisable(true);
        } else {
            btnBuscar.setDisable(false);
        }
    }
    
    private void botonAnadirPulsado(ActionEvent event) {
        boolean error = comprobarPatrones();
        
        if (!error) {
            lblBuscarAlumnoError.setText("OK"); //quitar mas tarde
        } else {
            lblBuscarAlumnoError.setText("NO"); //quitar mas tarde
        }
    }
    
    private void botonModificarPulsado(ActionEvent event) {
        boolean error = comprobarPatrones();
        
        if (!error) {
            //Hace cosas con el servidor
        }
    }
    
    private void botonEliminarPulsado(ActionEvent event) {
        //Hacer cosas con el servidor
    }
    
    private void botonLimpiarPulsado(ActionEvent event) {
        txtNombreCompleto.setText("");
        txtDni.setText("");
        txtUsuario.setText("");
        txtEmail.setText("");
        datePickerFechaNacimiento.getEditor().clear();
        txtBuscarAlumno.setText("");
        
        lblNombreCompletoError.setText("");
        lblDniError.setText("");
        lblUsuarioError.setText("");
        lblEmailError.setText("");
        lblFechaNacimientoError.setText("");
        lblBuscarAlumnoError.setText("");
        
        txtNombreCompleto.requestFocus();
        
        btnLimpiar.setDisable(true);
    }
    
    private void botonBuscarPulsado(ActionEvent event) {
    }
    
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
    
    private boolean comprobarPatrones() {
        boolean error = false;
        
        if (txtNombreCompleto.getText().isEmpty()) {
            lblNombreCompletoError.setText("");
        } else {
            Matcher matcher = VALID_NOMBRE.matcher(txtNombreCompleto.getText());
            if (!matcher.find()) {
                lblNombreCompletoError.setText("El nombre solo debe contener letras");
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
                lblUsuarioError.setText("El usuario no debe contener espacios");
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
}
