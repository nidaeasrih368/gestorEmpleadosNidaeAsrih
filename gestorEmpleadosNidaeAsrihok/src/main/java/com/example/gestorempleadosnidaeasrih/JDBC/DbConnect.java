package com.example.gestorempleadosnidaeasrih.JDBC;

import com.example.gestorempleadosnidaeasrih.Model.Trabajador;
import javafx.scene.control.Alert;

import java.sql.*;
import java.text.SimpleDateFormat;

public class DbConnect {
    String url;
    String user;
    String password;
    public Connection connection;

    public DbConnect() {
        this.url = "jdbc:mysql://localhost:3306/gestorempleadosnidaeasrih";
        this.user = "root";
        this.password = "root";

        try {
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión creada en: " + url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarTrabajador(Trabajador trabajador) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO trabajador (NOMBRE, PUESTO, SALARIO, FECHA) VALUES ('"
                    + trabajador.getNombre() + "', '"
                    + trabajador.getPuesto() + "', "
                    + trabajador.getSalario() + ", '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(trabajador.getFecha()) + "')";

            int result = statement.executeUpdate(sql);
            statement.close();

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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("El trabajador " + trabajador.getNombre() + "ha sido introducido correctamente");
            alert.showAndWait();
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
