module com.example.gestorempleadosnidaeasrih {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.gestorempleadosnidaeasrih to javafx.fxml;
    exports com.example.gestorempleadosnidaeasrih;
    exports com.example.gestorempleadosnidaeasrih.Controlador;
    opens com.example.gestorempleadosnidaeasrih.Controlador to javafx.fxml;
}