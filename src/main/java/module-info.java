module com.example.zpo_lab5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zpo_lab5 to javafx.fxml;
    exports com.example.zpo_lab5;
}