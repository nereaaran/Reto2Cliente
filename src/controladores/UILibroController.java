/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controlador de la vista UILibro que contiene los metodos para definir y
 * controlar su comportamiento.
 *
 * @author Nerea Aranguren
 */
public class UILibroController {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("controladores.UILibroController");

    /**
     * Variable que guarda los carácteres máximos de los campos de texto autor,
     * genero y editorial.
     */
    private static final int MAX_LENGHT_TEXTO = 50;

    /**
     * Variable que guarda los carácteres máximos del campo de texto titulo.
     */
    private static final int MAX_LENGHT_TITULO = 100;

    /**
     * Variable que guarda los carácteres máximos del campo de texto isbn.
     */
    private static final int MAX_LENGHT_ISBN = 13;

    /**
     * Variable que guarda los carácteres máximos del campo de texto cantidad
     * total.
     */
    private static final int MAX_LENGHT_CANTIDAD_TOTAL = 3;
    /*
     * Atributo estático y constante que guarda el patron correcto de titulo.
     */
    public static final Pattern VALID_TITULO_EDITORIAL = Pattern.compile("^[A-Z0-9]+$", Pattern.CASE_INSENSITIVE);

    /**
     * Atributo estático y constante que guarda el patron correcto de autor,
     * genero y editorial.
     */
    public static final Pattern VALID_AUTOR_GENERO = Pattern.compile("^[A-Z\\s]+$", Pattern.CASE_INSENSITIVE);
    /**
     * Atributo estático y constante que guarda el patron correcto de isbn y
     * cantidad total.
     */
    public static final Pattern VALID_NUMERO = Pattern.compile("^[0-9]", Pattern.CASE_INSENSITIVE);

    /**
     * Lista de elementos importados de la vista FXML que representan objetos.
     */
    @FXML
    private AnchorPane paneGeneralLibro;
    @FXML
    private AnchorPane paneSuperiorLibro;
    @FXML
    private Button btnAnadir;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Label lbIsbn;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblAutor;
    @FXML
    private Label lblEditorial;
    @FXML
    private Label lblGenero;
    @FXML
    private Label lblCantidadTotal;
    @FXML
    private Label lblDescargable;
    @FXML
    private TextField txtIsbn;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAutor;
    @FXML
    private TextField txtEditorial;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtCantidadTotal;
    @FXML
    private TextField txtLinkDescarga;
    @FXML
    private Label lblLinkDescarga;
    @FXML
    private CheckBox cbxDescargable;
    @FXML
    private Label lblGestionLibros;
    @FXML
    private TextField txtBuscarLibro;
    @FXML
    private Button btnBuscar;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem mnCerrarSesion;
    @FXML
    private Label lblTituloError;
    @FXML
    private Label lblAutorError;
    @FXML
    private Label lblGeneroError;
    @FXML
    private Label lblEditorialError;
    @FXML
    private Label lblCantidadTotalError;
    @FXML
    private Label lblIsbnError;
    @FXML
    private Label lblBuscarLibroError;
    @FXML
    private Label lblInformacion;
    @FXML
    private AnchorPane paneInferiorLibro;
    @FXML
    private TableView<?> tablaLibro;
    @FXML
    private TableColumn<?, ?> tituloCL;
    @FXML
    private TableColumn<?, ?> autorCL;
    @FXML
    private TableColumn<?, ?> editorialCL;
    @FXML
    private TableColumn<?, ?> isbnCL;
    @FXML
    private TableColumn<?, ?> generoCL;
    @FXML
    private TableColumn<?, ?> cantidadTotalCL;
    @FXML
    private TableColumn<?, ?> cantidadDisponibleCL;
    @FXML
    private TableColumn<?, ?> descargableCL;
    @FXML
    private TableColumn<?, ?> linkdescargaCL;
    /**
     * Variable de tipo stage que se usa para visualizar la ventana
     */
    private Stage stage;

    /**
     * Método que establece al escenario del UILibro como escenario principal.
     *
     * @param primaryStage El escenario principal.
     */
    public void setStage(Stage primaryStage) {
        LOGGER.info("Libro Controlador: Estableciendo stage");

        stage = primaryStage;
    }

    /**
     * Método que inicializa el escenario y los componentes de UILibro.
     *
     * @param root El objeto padre que representa el nodo root.
     */
    public void initStage(Parent root) {
        LOGGER.info("Libro Controlador: Iniciando stage");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gestión de libros");
        stage.setResizable(false);

        stage.onCloseRequestProperty().set(this::cerrarVentana);

        btnAnadir.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnLimpiar.setDisable(true);
        btnBuscar.setDisable(true);

        txtTitulo.requestFocus();

        txtBuscarLibro.textProperty().addListener(this::handleTextoCambiado);
        txtTitulo.textProperty().addListener(this::handleTextoCambiado);
        txtAutor.textProperty().addListener(this::handleTextoCambiado);
        txtEditorial.textProperty().addListener(this::handleTextoCambiado);
        txtGenero.textProperty().addListener(this::handleTextoCambiado);
        txtCantidadTotal.textProperty().addListener(this::handleTextoCambiado);
        txtIsbn.textProperty().addListener(this::handleTextoCambiado);
        txtLinkDescarga.textProperty().addListener(this::handleTextoCambiado);

        btnLimpiar.setOnAction(this::handleBtnLimpiar);
        btnAnadir.setOnAction(this::handleBtnAnadir);
        btnModificar.setOnAction(this::handleBtnModificar);
        btnEliminar.setOnAction(this::handleBtnEliminar);
        mnCerrarSesion.setOnAction(this::handleCerrarSesion);

        stage.show();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    private void handleBtnEliminar(ActionEvent event) {
        if (eliminarLibroVentana()) {
            ///////////////////////////////////////////////
            limpiarCamposTexto();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    private void handleBtnModificar(ActionEvent event) {
        if (patronesTextoBien()) {
            ///////////////////////////////////////////////
            limpiarCamposTexto();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    private void handleBtnAnadir(ActionEvent event) {
        if (patronesTextoBien()) {
            ///////////////////////////////////////////////
            limpiarCamposTexto();
        }
    }

    /**
     * Comprueba que los patrone de titulo, autor, editorial, genero, cantidad
     * total e isbn son correctos.
     *
     * @return Variable indicando si todos los patrones son correctos.
     */
    private boolean patronesTextoBien() {
        boolean patronesTextoBien = true;

        Matcher matcher = null;

        matcher = VALID_TITULO_EDITORIAL.matcher(txtTitulo.getText());
        if (!matcher.find()) {
            lblTituloError.setText("El titulo sólo debe contener letras y numeros");
            lblTituloError.setTextFill(Color.web("#FF0000"));
            patronesTextoBien = false;
        } else {
            lblTituloError.setText("");
        }
        matcher = VALID_AUTOR_GENERO.matcher(txtAutor.getText());
        if (!matcher.find()) {
            lblAutorError.setText("El autor sólo debe contener letras");
            lblAutorError.setTextFill(Color.web("#FF0000"));
            patronesTextoBien = false;
        } else {
            lblAutorError.setText("");
        }
        matcher = VALID_AUTOR_GENERO.matcher(txtGenero.getText());
        if (!matcher.find()) {
            lblGeneroError.setText("El genero sólo debe contener letras");
            lblGeneroError.setTextFill(Color.web("#FF0000"));
            patronesTextoBien = false;
        } else {
            lblGeneroError.setText("");
        }
        matcher = VALID_TITULO_EDITORIAL.matcher(txtEditorial.getText());
        if (!matcher.find()) {
            lblEditorialError.setText("La editorial sólo debe contener letras y numeros");
            lblEditorialError.setTextFill(Color.web("#FF0000"));
            patronesTextoBien = false;
        } else {
            lblEditorialError.setText("");
        }
        matcher = VALID_NUMERO.matcher(txtCantidadTotal.getText());
        if (!matcher.find()) {
            lblCantidadTotalError.setText("La cantidad sólo debe contener numeros");
            lblCantidadTotalError.setTextFill(Color.web("#FF0000"));
            patronesTextoBien = false;
        } else {
            lblCantidadTotalError.setText("");
        }
        matcher = VALID_NUMERO.matcher(txtIsbn.getText());
        if (!matcher.find()) {
            lblIsbnError.setText("El isbn sólo debe contener numeros");
            lblIsbnError.setTextFill(Color.web("#FF0000"));
            patronesTextoBien = false;
        } else {
            lblIsbnError.setText("");
        }
        return patronesTextoBien;
    }

    /**
     * Vacia los campos de texto y pone el checkBox a false.
     */
    private void limpiarCamposTexto() {
        txtTitulo.clear();
        txtAutor.clear();
        txtEditorial.clear();
        txtGenero.clear();
        txtCantidadTotal.clear();
        txtIsbn.clear();
        txtLinkDescarga.clear();
        cbxDescargable.setSelected(false);
    }

    /**
     * Llama a un metodo para limpiar los campos de texto..
     *
     * @param event El evento de acción.
     */
    private void handleBtnLimpiar(ActionEvent event) {
        limpiarCamposTexto();
    }

    /**
     * Se ejecuta cuando un campo de texto ha sido modificado. Identifica el
     * campo de texto modificado, comprueba que no se pase de los caracteres
     * máximos y llama a la funcion encargada de habilitar o deshabilitar los
     * botones.
     *
     * @param observable El valor que se observa.
     * @param oldValue El valor antiguo del observable.
     * @param newValue El valor nuevo del observable.
     */
    private void handleTextoCambiado(ObservableValue observable, String oldValue, String newValue) {
        StringProperty textProperty = (StringProperty) observable;
        TextField changedTextField = (TextField) textProperty.getBean();
        String changedTextFieldName = changedTextField.getId();

        textFieldOverMaxLength(changedTextField, changedTextFieldName);

        habilitarBotones();
    }

    /**
     * Habilita y deshabilita los botones en funcion de si hay campos de texto
     * con texto o no.
     */
    private void habilitarBotones() {
        btnBuscar.setDisable(txtBuscarLibro.getText().isEmpty());

        if (!camposTextoVacios()) {
            btnAnadir.setDisable(false);
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);
        } else {
            btnAnadir.setDisable(true);
            btnModificar.setDisable(true);
            btnEliminar.setDisable(true);
        }
        if (camposTextoConTexto()) {
            btnLimpiar.setDisable(false);
        } else {
            btnLimpiar.setDisable(true);
        }
    }

    /**
     * Comprueba si algún campo de texto contiene texto o un checkbox está
     * seleccionado.
     *
     * @return Variable indicando si hay algun campo de texto con texto o no.
     */
    private boolean camposTextoConTexto() {
        if (!txtTitulo.getText().isEmpty() || !txtAutor.getText().isEmpty()
                || !txtEditorial.getText().isEmpty() || !txtGenero.getText().isEmpty()
                || !txtCantidadTotal.getText().isEmpty() || !txtIsbn.getText().isEmpty()
                || !txtLinkDescarga.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Comprueba si algún campo de texto de Titulo, Autor, Editorial, Genero,
     * CantidadTotal o Isbn está vacio.
     *
     * @return Una variable indicando si hay algun campo de texto vacio o no.
     */
    private boolean camposTextoVacios() {
        if (txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty()
                || txtEditorial.getText().isEmpty() || txtGenero.getText().isEmpty()
                || txtCantidadTotal.getText().isEmpty() || txtIsbn.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Comprueba que el texto introducido no supera los 50 caracteres. Si se
     * supera no muestra ni recoge los nuevos caracteres introducidos.
     *
     * @param changedTextField El campo de texto modificado.
     * @param changedTextFieldName El id del campo modificado.
     */
    private void textFieldOverMaxLength(TextField changedTextField, String changedTextFieldName) {
        int maxLenght = 0;

        switch (changedTextFieldName) {
            case "txtBuscarLibro":
            case "txtAutor":
            case "txtEditorial":
            case "txtGenero":
                maxLenght = MAX_LENGHT_TEXTO;
                break;
            case "txtTitulo":
                maxLenght = MAX_LENGHT_TITULO;
                break;
            case "txtCantidadTotal":
                maxLenght = MAX_LENGHT_CANTIDAD_TOTAL;
                break;
            case "txtIsbn":
                maxLenght = MAX_LENGHT_ISBN;
                break;
            default:
                maxLenght = 200;
                break;
        }
        if (changedTextField.getText().length() > maxLenght) {
            String text = changedTextField.getText().substring(0, maxLenght);
            changedTextField.setText(text);
        }
    }

    /**
     * Ventana emergente que se abre cuando se pulsa el boton eliminar para
     * confirmar si se quiere eliminar el libro.
     *
     * @return Variable indicando si se quiere eliminar o no.
     */
    private boolean eliminarLibroVentana() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Eliminar libro");
        alert.setHeaderText(null);
        alert.setContentText("¿Seguro que quieres eliminar el libro?");

        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get().equals(ButtonType.YES)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Cuando se pulsa Cerrar Sesion se cierra la ventana y se abre la de
     * SignIn.
     *
     * @param event El evento de acción.
     */
    private void handleCerrarSesion(ActionEvent event) {
        LOGGER.info("Libro Controlador: Iniciando vista SignIn");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/UISignIn.fxml"));
            Parent root = (Parent) loader.load();
            UISignInController controller = ((UISignInController) loader.getController());
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
            LOGGER.info("Libro Controlador: Cerrando aplicacion");
            stage.close();
            Platform.exit();
        } else {
            event.consume();
            alert.close();
        }
    }
}
