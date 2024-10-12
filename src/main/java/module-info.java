module org.example.laba1_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.laba1_javafx to javafx.fxml;
    exports org.example.laba1_javafx;
}