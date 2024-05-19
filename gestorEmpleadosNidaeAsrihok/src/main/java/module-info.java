module com.example.gestorempleadosnidaeasrih {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.gestorempleadosnidaeasrih to javafx.fxml;
    exports com.example.gestorempleadosnidaeasrih;
    exports com.example.gestorempleadosnidaeasrih.Controller;
    opens com.example.gestorempleadosnidaeasrih.Controller to javafx.fxml;
}