/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import static entidad.TipoUsuario.*;
import entidad.Usuario;
import excepcion.*;
import factorias.GestionFactoria;
import interfaces.UsuarioGestion;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controlador de la vista UISignIn que contiene los metodos para definir y
 * controlar su comportamiento.
 *
 * @author Nerea Aranguren y Cristina Milea
 */
public class UISignInController {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("controladores.UISignInController");

    /**
     * Variable que guarda los carácteres máximos de los campos de texto.
     */
    private static final int MAX_LENGHT = 50;
    /**
     * Variable que guarda el patrón de carácteres aceptables para la
     * contraseña.
     */
    public static final Pattern VALID_PASSWORD = Pattern.compile("^[a-zA-Z0-9]+$");
    /**
     * Elemento tipo pane importado de la vista FXML.
     */
    @FXML
    private Pane paneSignIn;
    /**
     * Elemento tipo label importado del FXML que referencia a Usuario.
     */
    @FXML
    private Label lblUsuario;
    /**
     * Elemento tipo TextField importado del FXML que referencia a Usuario.
     */
    @FXML
    private TextField txtUsuario;
    /**
     * Elemento tipo label importado del FXML que referencia a Contraseña.
     */
    @FXML
    private Label lblContrasenia;
    /**
     * Elemento tipo passwordField importado del FXML que referencia a
     * Contraseña.
     */
    @FXML
    private PasswordField txtContrasenia;
    /**
     * Elemento tipo botón importado del FXML que referencia a IniciarSesion.
     */
    @FXML
    private Button btnIniciarSesion;
    /**
     * Elemento tipo botón importado del FXML que referencia a Registrate.
     */
    @FXML
    private Button btnRegistrate;
    /**
     * Elemento tipo botón importado del FXML que referencia a Bibliotecario.
     */
    @FXML
    private Button btnBibliotecario;
    /**
     * Elemento tipo botón importado del FXML que referencia a Profesor.
     */
    @FXML
    private Button btnProfesor;
    /**
     * Elemento tipo label importado del FXML que referencia a Título.
     */
    @FXML
    private Label lblTitulo;
    /**
     * Elemento tipo label importado del FXML que referencia a NoTienesCuenta.
     */
    @FXML
    private Label lblNoTienesCuenta;
    /**
     * Elemento tipo label importado del FXML que referencia a ContraseniaError.
     */
    @FXML
    private Label lblContraseniaError;
    /**
     * Elemento tipo label importado del FXML que referencia a UsuarioError.
     */
    @FXML
    private Label lblUsuarioError;
    /**
     * Elemento tipo hyperlink importado del FXML que referencia a
     * ContraseniaOlvidada.
     */
    @FXML
    private Hyperlink linkContraseniaOlvidada;
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
        LOGGER.info("SignIn Controlador: Estableciendo stage");

        stage = primaryStage;
    }

    /**
     * Método que inicializa el escenario y los componentes del Login.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("SignIn Controlador: Iniciando stage principal");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign In");
        stage.setResizable(false);

        stage.onCloseRequestProperty().set(this::cerrarVentana);

        txtUsuario.requestFocus();
        btnIniciarSesion.setDisable(true);

        btnBibliotecario.setOnAction(this::handleBotonBibliotecario);
        btnProfesor.setOnAction(this::handleBotonProfesor);

        btnIniciarSesion.setOnAction(this::handleBotonIniciarSesion);
        btnRegistrate.setOnAction(this::handleBotonRegistro);
        linkContraseniaOlvidada.setOnAction(this::handleBotonContraseniaOlvidada);

        txtUsuario.textProperty().addListener(this::handleTextoCambiado);
        txtContrasenia.textProperty().addListener(this::handleTextoCambiado);

        stage.show();
    }

    /**
     * Método que abre la venta UILibro.
     *
     * @param event El evento de acción.
     */
    private void handleBotonBibliotecario(ActionEvent event) {
        LOGGER.info("SignIn Controlador: Iniciando vista UILibro");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UILibro.fxml"));
            Parent root = (Parent) loader.load();
            UILibroController controller = ((UILibroController) loader.getController());
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Método que abre la venta UIGrupo.
     *
     * @param event El evento de acción.
     */
    private void handleBotonProfesor(ActionEvent event) {
        LOGGER.info("SignIn Controlador: Iniciando vista UIGrupo");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UIGrupo.fxml"));
            Parent root = (Parent) loader.load();
            UIGrupoController controller = ((UIGrupoController) loader.getController());
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Método que comprueba los errores posibles y si no hay ninguno carga y
     * abre la venta UIGrupo.
     *
     * @param event El evento de acción.
     */
    private void handleBotonIniciarSesion(ActionEvent event) {
        LOGGER.info("SignIn Controlador: Pulsado boton Iniciar sesion");

        try {
            UsuarioGestion usuarioGestion = GestionFactoria.getUsuarioGestion();

            //Comprueba si existe el login
            LOGGER.info("SignIn Controlador: Comprobando si existe el login");

            Collection<Usuario> usuario = usuarioGestion.buscarUsuarioPorLoginSignIn(txtUsuario.getText());

            //Comprueba si el login y la contraseña están bien
            LOGGER.info("SignIn Controlador: Comprobando login y contraseña");

            usuarioGestion.buscarUsuarioPorLoginYContrasenia(txtUsuario.getText(), txtContrasenia.getText());

            Date date = new Date(System.currentTimeMillis());

            for (Usuario u : usuario) {
                u.getPrivilege();

                switch (u.getTipoUsuario()) {
                    case BIBLIOTECARIO: {
                        //Abre la vista de UILibro
                        LOGGER.info("SignIn Controlador: Abriendo la vista UILibro");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UILibro.fxml"));
                        Parent root = (Parent) loader.load();
                        UILibroController controller = ((UILibroController) loader.getController());
                        controller.setStage(stage);
                        controller.initStage(root);
                        break;
                    }
                    case PROFESOR: {
                        //Abre la vista de UIGrupo
                        
                        LOGGER.info("SignIn Controlador: Abriendo la vista UIGrupo");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UIGrupo.fxml"));
                        Parent root = (Parent) loader.load();
                        
                        UIGrupoController controller = ((UIGrupoController) loader.getController());
                       // controller.setUser(u);
                        controller.setStage(stage);
                        controller.initStage(root);
                        break;
                    }
                    default:
                        lblContraseniaError.setText("No estás autorizado para realizar esta acción");
                        lblContraseniaError.setTextFill(Color.web("#FF0000"));
                        break;
                }
            }
        } catch (LoginNoExisteException lne) {
            LOGGER.severe(lne.getMessage());
            lblUsuarioError.setText("Usuario no encontrado");
            lblUsuarioError.setTextFill(Color.web("#FF0000"));
        } catch (UsuarioNoExisteException une) {
            LOGGER.severe(une.getMessage());
            lblContraseniaError.setText("Contraseña incorrecta");
            lblContraseniaError.setTextFill(Color.web("#FF0000"));
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Se llama cuando se modifica cualquier campo de texto. Comprueba que los
     * campos de texto no estan vacios y no superan los 50 caracteres. Si se
     * cumplen los requisitos se habilita el boton de Iniciar Sesion, si no se
     * cumplen lo deshabilita.
     *
     * @param observable El valor que se observa.
     * @param oldValue El valor antiguo del observable.
     * @param newValue El valor nuevo del observable.
     */
    private void handleTextoCambiado(ObservableValue observable, String oldValue, String newValue) {
        // Identifica el TextField que lo ha llamado
        StringProperty textProperty = (StringProperty) observable;
        // Guarda el textField
        TextField changedTextField = (TextField) textProperty.getBean();

        if (!textFieldOver50(changedTextField) && !txtUsuario.getText().isEmpty() && !txtContrasenia.getText().isEmpty()) {
            btnIniciarSesion.setDisable(false);
        } else {
            btnIniciarSesion.setDisable(true);
        }
    }

    /**
     * Comprueba que el texto introducido no supera los 50 caracteres. Si se
     * supera no muestra ni recoge los nuevos caracteres introducidos.
     *
     * @param changedTextField El campo de texto modificado.
     * @return Variable indicando si supera los 50 caracteres o no.
     */
    private boolean textFieldOver50(TextField changedTextField) {
        boolean over50 = false;

        if (changedTextField.getText().length() > MAX_LENGHT) {
            String text = changedTextField.getText().substring(0, MAX_LENGHT);
            changedTextField.setText(text);
            over50 = true;
        }
        return over50;
    }

    /**
     * Método que carga y abre la venta UIRestaurarContrasenia.
     *
     * @param event El evento de acción.
     */
    private void handleBotonContraseniaOlvidada(ActionEvent event) {
        LOGGER.info("SignIn Controlador: Iniciando vista Restaurar Contrasenia");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UIRestaurarContrasenia.fxml"));
            Parent root = (Parent) loader.load();
            UIRestaurarContraseniaController controller = ((UIRestaurarContraseniaController) loader.getController());
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Método que carga y abre la venta UISignUp.
     *
     * @param event El evento de acción.
     */
    private void handleBotonRegistro(ActionEvent event) {
        LOGGER.info("SignIn Controlador: Iniciando vista Sign Up");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UISignUp.fxml"));
            Parent root = (Parent) loader.load();
            UISignUpController controller = ((UISignUpController) loader.getController());
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
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
            stage.close();
            Platform.exit();
        } else {
            event.consume();
            alert.close();
        }
    }
}
