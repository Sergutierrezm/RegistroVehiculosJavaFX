module com.example.nf1actividad2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nf1actividad2 to javafx.fxml;
    exports com.example.nf1actividad2;
}