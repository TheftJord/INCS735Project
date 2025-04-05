module com.incs735 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.incs735 to javafx.fxml;
    exports com.incs735;
}
