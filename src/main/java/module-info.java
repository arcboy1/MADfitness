module com.example.madfitness {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.madfitness to javafx.fxml;
    exports com.example.madfitness;
}