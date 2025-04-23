module com.incs735 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires java.sql;
    requires com.google.gson;
    requires javafx.graphics;

    opens com.incs735 to javafx.fxml, com.google.gson;
    exports com.incs735;
}
