module com.example.cincuentazoproyecto {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.cincuentazoproyecto.controller to javafx.fxml;

    exports com.example.cincuentazoproyecto;
    exports com.example.cincuentazoproyecto.model;
}