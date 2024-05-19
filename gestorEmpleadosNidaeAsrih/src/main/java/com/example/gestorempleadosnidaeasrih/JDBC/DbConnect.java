package com.example.gestorempleadosnidaeasrih.JDBC;

import com.example.gestorempleadosnidaeasrih.Modelo.Trabajador;
import javafx.scene.control.Alert;

import java.sql.*;
import java.text.SimpleDateFormat;

// clase connection String para conectar la bbdd
public class DbConnect {
    String url;
    String user;
    String password;
    public Connection connection; // sin este atributo no puedo abrir la conexion a la bbdd

    public DbConnect() {
        this.url = "jdbc:mysql://localhost:3306/gestorempleadosnidaeasrih"; // refencia a la base de datos
        this.user = "root";
        this.password = "root";

        // Comprobar que la conexion a la base de datos es correcta.
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión creada en: " + url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        // INsert en una tabla de sql.
    public void insertarTrabajador(Trabajador trabajador) {

        // Crear un nuevo objeto de tipo Statement ("sentencia"),
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO trabajador (NOMBRE, PUESTO, SALARIO, FECHA) VALUES ('"
                    + trabajador.getNombre() + "', '"
                    + trabajador.getPuesto() + "', "
                    + trabajador.getSalario() + ", '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(trabajador.getFecha()) + "')";
                    // registrar hora en la que se ha insertado el trabajador, usamos tipo Date.
            int result = statement.executeUpdate(sql); // resultado int (num(bien) o 0(mal)). Ejecutar sql que se ha insertado.
            statement.close(); // cerrar el statement

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarNuevoTrabajador(Trabajador trabajador) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO trabajador (NOMBRE, PUESTO, SALARIO, FECHA) VALUES ('"
                    + trabajador.getNombre() + "', '"
                    + trabajador.getPuesto() + "', "
                    + trabajador.getSalario() + ", '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(trabajador.getFecha()) + "')";

            int result = statement.executeUpdate(sql);
            // Alerta despues de insertar el trabajador.

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("El trabajador " + trabajador.getNombre() + "ha sido introducido correctamente");
            alert.showAndWait(); // muestra la alerta hasta que el usuario clica en aceptar o en la x.
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTrabajadorPorId(int id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM trabajador WHERE ID = " + id;
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
