module com.example.soen6611_jfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.soen6611.app to javafx.fxml;
    exports com.soen6611.app;
}
