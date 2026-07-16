module com.example.cincuentazoproyecto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cincuentazoproyecto to javafx.fxml;
    exports com.example.cincuentazoproyecto;
}