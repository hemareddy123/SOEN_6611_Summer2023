package com.soen6611.app;

import com.soen6611.model.UIGenerator;
import javafx.application.Application;
import javafx.stage.Stage;

public class MetricsticsApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        new UIGenerator().generate(stage);
    }
}
