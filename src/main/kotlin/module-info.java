module com.cadastrousuariokotlin {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.sql;


    opens com.view to javafx.fxml;
    exports com.view;

    opens com.controller to javafx.fxml;
    exports com.controller;
}