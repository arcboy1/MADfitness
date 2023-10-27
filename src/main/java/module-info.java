module com.example.madfitness {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.madfitness to javafx.fxml;
    exports com.example.madfitness;
    exports com.example.madfitness.GUI;
    opens com.example.madfitness.GUI to javafx.fxml;
}