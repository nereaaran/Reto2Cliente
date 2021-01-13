/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
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
    public static final Pattern VALID_DNI = Pattern.compile("^[XYZ][0-9]{7}[A-Z]", Pattern.CASE_INSENSITIVE);
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

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void initStage(Parent ventana) {
        Scene scene = new Scene(ventana);
        stage.setScene(scene);

        stage.setTitle("Gestion de alumnos");
        stage.setResizable(false);

        /*btnAnadir.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnLimpiar.setDisable(true);
        btnBuscar.setDisable(true);*/
        txtNombreCompleto.textProperty().addListener(this::manejoCamposTexto);
        txtDni.textProperty().addListener(this::longitudMaximaDni);
        txtUsuario.textProperty().addListener(this::manejoCamposTexto);
        txtEmail.textProperty().addListener(this::manejoCamposTexto);

        stage.show();
    }

    public void manejoCamposTexto(ObservableValue observable, String oldValue, String newValue) {
        StringProperty textProperty = (StringProperty) observable;
        TextField textField = (TextField) textProperty.getBean();
        String idTextField = textField.getId();

        boolean mayorDe50Caracteres = longitudMaximaGeneral(idTextField, textField);
        
        if (!mayorDe50Caracteres) {
            //habilitarBotones();
        }
    }

    private boolean camposTextoVacios() {
        boolean vacios = false;
        if (txtNombreCompleto.getText().isEmpty() || txtDni.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            vacios = true;
        }
        return vacios;
    }

    /*private void habilitarBotones() {
        if (!camposTextoVacios() && nombreBien) {
            idButtonSignUp.setDisable(false);
        } else {
            idButtonSignUp.setDisable(true);
        }
    }*/

    private boolean longitudMaximaGeneral(String idTextField, TextField textField) {
        boolean error = false;
        boolean masDe50 = false;

        if (textField.getText().length() > MAX_LENGHT) {
            String texto = textField.getText().substring(0, MAX_LENGHT);
            textField.setText(texto);

            masDe50 = true;

            switch (idTextField) {
                case ("txtNombreCompleto"):
                    error = true;
                    lblNombreCompletoError.setText("El nombre debe ser menor de 50 caracteres");
                    lblNombreCompletoError.setTextFill(Color.web("#FF0000"));
                case ("txUsuario"):
                    error = true;
                    lblUsuarioError.setText("El usuario debe ser menor de 50 caracteres");
                    lblUsuarioError.setTextFill(Color.web("#FF0000"));
                case ("txtEmail"):
                    error = true;
                    lblEmailError.setText("El email debe ser menor de 50 caracteres");
                    lblEmailError.setTextFill(Color.web("#FF0000"));
            }
        } else {
            switch (idTextField) {
                case ("lblNombreCompletoError"):
                    lblNombreCompletoError.setText("");
                case ("lblUsuarioError"):
                    lblUsuarioError.setText("");
                case ("lblEmailError"):
                    lblEmailError.setText("");
            }
        }

        return masDe50;
    }

    private void longitudMaximaDni(ObservableValue observable, String oldValue, String newValue) {
        if (txtDni.getText().length() > MAX_LENGHT_DNI) {
            String dni = txtDni.getText().substring(0, MAX_LENGHT_DNI);
            txtDni.setText(dni);
        } else {
            lblDniError.setText("");
        }
    }

    /*private boolean comprobarPatrones(String idTextField) {
        boolean patronCorrecto = true;

        switch (idTextField) {
            case ("txtNombreCompleto"):
                Matcher matcher = VALID_NOMBRE.matcher(txtDni.getText());
                if (!matcher.find()) {
                    patronCorrecto = false;
                    lblDniError.setText("DNI inválido");
                    lblDniError.setTextFill(Color.web("#FF0000"));
                }
            case ("txtDni"):
                Matcher matcher = VALID_DNI.matcher(txtDni.getText());
                if (!matcher.find()) {
                    patronCorrecto = false;
                    lblDniError.setText("DNI inválido");
                    lblDniError.setTextFill(Color.web("#FF0000"));
                }

        }

        if (idTextField.equals("idTextName")) {
            Matcher matcher = VALID_NOMBRE.matcher(idTextName.getText());
            if (!matcher.find()) {
                nombreBien = false;
                correctPattern = false;
                idLabelNameError.setText("Name must only contain letters");
                idLabelNameError.setTextFill(Color.web("#FF0000"));
            } else {
                nombreBien = true;
            }
        }
        return correctPattern;
    }*/
    private void longitudMaximaNombreCompleto(ObservableValue observable, String oldValue, String newValue) {
        if (txtNombreCompleto.getText().length() > MAX_LENGHT) {
            String nombreCompleto = txtNombreCompleto.getText().substring(0, MAX_LENGHT);
            txtNombreCompleto.setText(nombreCompleto);

            lblNombreCompletoError.setText("Nombre debe ser menor de 50 caracteres");
            lblNombreCompletoError.setTextFill(Color.web("#FF0000"));
        } else {
            lblNombreCompletoError.setText("");
        }
    }

    private void comprobarPatronNombre() {
        if (txtNombreCompleto.getText().isEmpty()) {
            lblNombreCompletoError.setText("");
        } else {
            Matcher matcher = VALID_NOMBRE.matcher(txtNombreCompleto.getText());
            if (!matcher.find()) {
                lblNombreCompletoError.setText("El nombre solo debe contener letras");
                lblNombreCompletoError.setTextFill(Color.web("#FF0000"));
            }
        }
    }

    private void comprobarPatronDNI() {
        if (txtDni.getText().isEmpty()) {
            lblDniError.setText("");
        } else {
            Matcher matcher = VALID_DNI.matcher(txtDni.getText());
            if (!matcher.find()) {
                lblDniError.setText("DNI inválido");
                lblDniError.setTextFill(Color.web("#FF0000"));
            }
        }
    }

    private void longitudMaximaUsuario(ObservableValue observable, String oldValue, String newValue) {
        if (txtUsuario.getText().length() == MAX_LENGHT) {
            String usuario = txtUsuario.getText().substring(0, MAX_LENGHT);
            txtUsuario.setText(usuario);

            lblUsuarioError.setText("Usuario debe ser menor de 50 caracteres");
            lblUsuarioError.setTextFill(Color.web("#FF0000"));
        } else {
            lblUsuarioError.setText("");
        }
    }

    private void comprobarPatronUsuario() {
        if (txtUsuario.getText().isEmpty()) {
            lblUsuarioError.setText("");
        } else {
            Matcher matcher = VALID_USUARIO.matcher(txtUsuario.getText());
            if (!matcher.find()) {
                lblUsuarioError.setText("El usuario no debe contener espacios");
                lblUsuarioError.setTextFill(Color.web("#FF0000"));
            }
        }
    }

    private void longitudMaximaEmail(ObservableValue observable, String oldValue, String newValue) {
        if (txtEmail.getText().length() == MAX_LENGHT) {
            String email = txtEmail.getText().substring(0, MAX_LENGHT);
            txtEmail.setText(email);

            lblEmailError.setText("El email debe ser menor de 50 caracteres");
            lblEmailError.setTextFill(Color.web("#FF0000"));
        } else {
            lblEmailError.setText("");
        }
    }

    private void comprobarPatronEmail() {
        if (txtEmail.getText().isEmpty()) {
            lblEmailError.setText("");
        } else {
            Matcher matcher = VALID_EMAIL.matcher(txtEmail.getText());
            if (!matcher.find()) {
                lblEmailError.setText("Direccion de email inválido");
                lblEmailError.setTextFill(Color.web("#FF0000"));
            }
        }
    }
}
