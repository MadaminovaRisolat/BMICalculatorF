module org.example.demofinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.demofinal to javafx.fxml;
    exports org.example.demofinal;
}