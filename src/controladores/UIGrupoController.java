/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidad.Grupo;
import entidad.Libro;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
    private AnchorPane paneInferiorGrupo;
    
     @FXML
    private Label lblListaGrupos;
     @FXML
    private Label lblLibrosAsignados;
     @FXML
    private Label lblGestionGrupos;
     @FXML
    private Label lblNombreGrupo;
     @FXML
    private Label lblDescripcion;
     @FXML
    private Label lblNombreGrupoError;
     @FXML
    private Label lblBuscarGrupoError;
     @FXML
    private Label lblDescripcionError;
    
     @FXML
    private Button btnModificar;
     @FXML
    private Button btnEliminar;
     @FXML
    private Button btnLimpiar;
     @FXML
    private Button btnAnadir;
     @FXML
    private Button btnGestionarAlumno;
     @FXML
    private Button btnBuscar;
     @FXML
    private Button btnAnadirLibro;
     @FXML
    private Button btnEliminarLibro;
    
    @FXML
    private TextField txtNombreGrupo;
     @FXML
    private TextField txtDescripcion;
     @FXML
    private TextField txtBuscarGrupo;
     @FXML
    private MenuBar munuBar;
     @FXML
    private MenuItem mnMiPerfil;
     @FXML
    private MenuItem mnCerrarSesion;
    
   
     @FXML
    private TableView tablaGrupo;
     @FXML
    private TableColumn nombreGrupoCL;
     @FXML
    private TableColumn descripcionCL;
     @FXML
    private TableColumn numAlumnosCL;
     @FXML
    private TableColumn nombreProfesorCL;
     @FXML
    private TableView tablaLibroAsignados;
     @FXML
    private TableColumn tituloCL;
     @FXML
    private TableColumn fechaInicioCL;
     @FXML
    private TableColumn fechaFinCL;
    
    //Para seleccionar la posicion.
    private int posicionGrupoEnTabla;
    
    ObservableList<Grupo> grupos;
    ObservableList<Libro> libros;
    
     @FXML
    private void anadirGrupo(ActionEvent event) {
        Grupo grupo = new Grupo();
        //libro.setIdLibro(Integer.valueOf(idLibroCL.getText()));
        grupo.setNombre(txtNombreGrupo.getText());
        grupo.setDescripcion(txtDescripcion.getText());
        grupos.add(grupo);
    }
    
     @FXML
    private void modificarLibro(ActionEvent event) {
        Grupo grupo = new Grupo();
        grupo.setNombre(txtNombreGrupo.getText());
        grupo.setDescripcion(txtDescripcion.getText());
        grupos.set(posicionGrupoEnTabla, grupo);
    }
    
     @FXML
    private void eliminarLibro(ActionEvent event) {
        grupos.remove(posicionGrupoEnTabla);
    }
    
    //Para seleccionar un celda de la tabla
    private final ListChangeListener<Grupo> selecionarTablaGrupo=
        new ListChangeListener<Grupo>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Grupo> l){
                 ponerGrupoSelecccionado();   
            }
        };
    
    public Grupo getTablaGrupoSeleccionada(){
        if (tablaGrupo != null) {
            List<Grupo> tabla = (List<Grupo>) tablaGrupo.getSelectionModel().getSelectedItems();
            if (tabla.size()==1) {
                final Grupo competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }
    
    private void ponerGrupoSelecccionado() {
        final Grupo grupo = getTablaGrupoSeleccionada();
        posicionGrupoEnTabla = grupos.indexOf(grupo);
        
        if (grupo != null) {
          
            //Rellenamos los textField con los datos correspondientes
            txtNombreGrupo.setText(grupo.getNombre());
            txtDescripcion.setText(grupo.getDescripcion());  
            //Los botones en su estado correspondientes
            btnModificar.setDefaultButton(false);
            btnEliminar.setDefaultButton(false);
            btnAnadir.setDisable(true);
        }
    
    }
        
    private void inicializarTablaGrupo() {
        nombreGrupoCL.setCellValueFactory(new PropertyValueFactory("Nombre"));
        descripcionCL.setCellValueFactory(new PropertyValueFactory("Descripción"));
        numAlumnosCL.setCellValueFactory(new PropertyValueFactory("Nº de alumnos"));
         
        grupos = FXCollections.observableArrayList();
        tablaGrupo.setItems(grupos);
   }
    
    private void inicializarTablaLibroAsignado() {
        tituloCL.setCellFactory(new PropertyValueFactory("Título"));
        fechaInicioCL.setCellFactory(new PropertyValueFactory("Fecha inicio"));
        fechaFinCL.setCellFactory(new PropertyValueFactory("Fecha fin"));
        
        grupos = FXCollections.observableArrayList();
        tablaLibroAsignados.setItems(libros);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.inicializarTablaGrupo();
        this.inicializarTablaLibroAsignado();
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        
        //Seleccionar las tuplas de las personas
        final ObservableList<Grupo> tablaGrupoSel = tablaGrupo.getSelectionModel().getSelectedItems();
        final ObservableList<Libro> tablaLibroSel = tablaLibroAsignados.getSelectionModel().getSelectedItems();
        tablaGrupoSel.addListener(selecionarTablaGrupo);
        
    
    }        

    
}

   
