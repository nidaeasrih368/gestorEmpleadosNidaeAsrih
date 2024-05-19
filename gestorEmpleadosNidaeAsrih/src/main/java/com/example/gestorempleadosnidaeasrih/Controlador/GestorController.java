package com.example.gestorempleadosnidaeasrih.Controlador;

import com.example.gestorempleadosnidaeasrih.JDBC.DbConnect;
import com.example.gestorempleadosnidaeasrih.Modelo.Trabajador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class GestorController {

    DbConnect db = new DbConnect(); // Conexion a la base de datos.


    // ID hechos en scene builder
    @FXML
    private ComboBox<String> cbPuesto; // listado de puestos de tipo combobox de scene builder

    @FXML
    private TextField tbNombre;

    @FXML
    private TextField tbSalario;

    @FXML
    private Button btnInsertar;

    @FXML
    private Button btnRefrescar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnCargarDatos;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblID;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblPuesto;

    @FXML
    private Label lblSalario;


    @FXML
    private ListView<Trabajador> lvwListado;  // Lista de los nombres de trabajadores. Lo que se ve en la ventana de consultar

    @FXML
    public void initialize() { // "constructor" que va a inicializar. Si creo un nuevo metodo llamrlo en este metodo para q funcione.
        setPuestos();
        cargarDatos();
        refreshListview();
        insert();
        seleccionarTrabajador();
        eliminarTrabajadorPorId();

    }

    private void insert() {
        btnInsertar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               // db = new DbConnect(); no hace falta

                String nombre = tbNombre.getText();
                String puesto = cbPuesto.getValue();
                int salario = Integer.parseInt(tbSalario.getText());
                Date fecha = new Date();

                Trabajador trabajador = new Trabajador(nombre, puesto, salario, new Date());
                db.insertarNuevoTrabajador(trabajador);

                tbNombre.clear();
                tbSalario.clear();
            }
        });
    }

    private void setPuestos() {

        //metodo para cargar el comboBox
        ObservableList<String> puestos = FXCollections.observableArrayList(
                "Scada Manager",
                "Sales Manager",
                "Product Owner",
                "Product Manager",
                "Analyst Programmer",
                "Junior Programmer"
        );
        cbPuesto.setItems(puestos);
    }

    private void refreshListview() {
        //boton actualizar
        // id del boton de scenebuilder.
        btnRefrescar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               // db = new DbConnect();
                try {
                    Statement statement = db.connection.createStatement();
                    String sql = "SELECT * FROM trabajador";

                    ResultSet resultSet = statement.executeQuery(sql);
                    ObservableList<Trabajador> trabajadores = FXCollections.observableArrayList();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        String nombre = resultSet.getString("NOMBRE");
                        String puesto = resultSet.getString("PUESTO");
                        double salario = resultSet.getDouble("SALARIO");
                        Date fecha = resultSet.getDate("FECHA");

                        Trabajador trabajador = new Trabajador(id, nombre, puesto, (int) salario, fecha);
                        trabajadores.add(trabajador);

                    }
                    lvwListado.setItems(trabajadores); // grabar los nombres en el listado
                    lvwListado.refresh(); // actualizo listado
                    lvwListado.setCellFactory(new Callback<ListView<Trabajador>, ListCell<Trabajador>>() {
                        @Override
                        public ListCell<Trabajador> call(ListView<Trabajador> param) {
                            return new ListCell<Trabajador>() {
                                @Override
                                protected void updateItem(Trabajador item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty || item == null) {
                                        setText(null);
                                    } else {
                                        setText(item.getNombre()); // Aquí se muestra el nombre del trabajador
                                    }
                                }
                            };
                        }
                    });

                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void seleccionarTrabajador(){
        lvwListado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Actualizar el Label con los atributos del trabajador seleccionado
                int id = newValue.getID();
                String nombre = newValue.getNombre();
                String puesto = newValue.getPuesto();
                double salario = newValue.getSalario();
                Date fecha = newValue.getFecha();

                lblID.setText(Integer.toString(id));
                lblNombre.setText(nombre);
                lblPuesto.setText(puesto);
                lblSalario.setText(Double.toString(salario));
                lblFecha.setText(fecha.toString());
            }
        });

    }




    private void cargarDatos() {
        btnCargarDatos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                db = new DbConnect();
                try (Scanner entrada = new Scanner(new File("src/main/resources/trabajadores.txt"))) {
                    while (entrada.hasNextLine()) {
                        String linea = entrada.nextLine();
                        String[] partes = linea.split(";");

                        if (partes.length >= 3) {
                            String nombre = partes[0];
                            String puesto = partes[1];
                            int salario = Integer.parseInt(partes[2]);

                            Trabajador trabajador = new Trabajador(nombre, puesto, salario, new Date());
                            db.insertarTrabajador(trabajador);
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }


   private void eliminarTrabajadorPorId() {
        btnEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int id = Integer.parseInt(lblID.getText());
                try {
                    Statement statement = db.connection.createStatement();
                    String sql = "DELETE FROM TRABAJADOR WHERE trabajador.id = " + id;
                    ResultSet resultSet = statement.executeQuery(sql);


                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}