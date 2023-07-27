package com.soen6611.model;

import com.soen6611.util.Util;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class UIGenerator {

    private Statistics statistics;

    public void generate(Stage stage) {
        stage.setTitle("METRICSTICS");
        Image icon = new Image("images/icon.png");
        stage.getIcons().add(icon);

        // create a grid pane as the root layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        statistics = new Statistics();

        // add buttons and labels
        TextArea dataSet = new TextArea("0");
        dataSet.setPrefWidth(120);
        dataSet.setWrapText(true);
        dataSet.setEditable(false);
        gridPane.add(dataSet, 0, 1, 7, 1);

        Button generateBtn = Util.createOperatorButton("Generate Data");
        TextField countField = new TextField("1000");
        generateBtn.setPrefWidth(100);
        generateBtn.setOnAction(event -> initializeDataSet(dataSet, countField));
        gridPane.add(generateBtn, 0, 0, 3, 1);
        gridPane.add(countField, 2, 0, 2, 1);

        Button minBtn = Util.createOperatorButton("m"); // min
        gridPane.add(minBtn, 0, 2);
        Label minRes = new Label("0");
        gridPane.add(minRes, 0, 3);
        minBtn.setOnAction(event -> statistics.calculateAndDisplay(minRes, "m"));

        Button maxBtn = Util.createOperatorButton("M"); // max
        gridPane.add(maxBtn, 1, 2);
        Label maxRes = new Label("0");
        gridPane.add(maxRes, 1, 3);
        maxBtn.setOnAction(event -> statistics.calculateAndDisplay(maxRes, "M"));

        Button modeBtn = Util.createOperatorButton("o"); // mode
        gridPane.add(modeBtn, 2, 2);
        Label modeRes = new Label("0");
        gridPane.add(modeRes, 2, 3);
        modeBtn.setOnAction(event -> statistics.calculateAndDisplay(modeRes, "o"));

        Button medianBtn = Util.createOperatorButton("d"); // median
        gridPane.add(medianBtn, 3, 2);
        Label medianRes = new Label("0");
        gridPane.add(medianRes, 3, 3);
        medianBtn.setOnAction(event -> statistics.calculateAndDisplay(medianRes, "d"));

        Button arithmeticMeanBtn = Util.createOperatorButton("μ"); // arithmetic mean
        gridPane.add(arithmeticMeanBtn, 4, 2);
        Label arithmeticMeanRes = new Label("0");
        gridPane.add(arithmeticMeanRes, 4, 3);
        arithmeticMeanBtn.setOnAction(event -> statistics.calculateAndDisplay(arithmeticMeanRes, "μ"));

        Button meanAbsoluteDeviationBtn = Util.createOperatorButton("MAD"); // mean absolute deviation
        meanAbsoluteDeviationBtn.setPrefWidth(50);
        gridPane.add(meanAbsoluteDeviationBtn, 5, 2);
        Label meanAbsoluteDeviationRes = new Label("0");
        gridPane.add(meanAbsoluteDeviationRes, 5, 3);
        meanAbsoluteDeviationBtn.setOnAction(event -> statistics.calculateAndDisplay(meanAbsoluteDeviationRes, "MAD"));

        Button standardDeviationBtn = Util.createOperatorButton("σ"); // standard deviation
        gridPane.add(standardDeviationBtn, 6, 2);
        Label standardDeviationRes = new Label("0");
        gridPane.add(standardDeviationRes, 6, 3);
        standardDeviationBtn.setOnAction(event -> statistics.calculateAndDisplay(standardDeviationRes, "σ"));

        List<Label> allDisplays = Arrays.asList(minRes, maxRes, modeRes, medianRes, arithmeticMeanRes, meanAbsoluteDeviationRes, standardDeviationRes);

        Button calculateAllBtn = Util.createOperatorButton("Calculate All"); // calculate all
        calculateAllBtn.setPrefWidth(100);
        gridPane.add(calculateAllBtn, 0, 4, 3 , 1);
        calculateAllBtn.setOnAction(event -> statistics.calculateAll(allDisplays));

        Button clearAllBtn = Util.createOperatorButton("Clear All"); // calculate all
        clearAllBtn.setPrefWidth(100);
        gridPane.add(clearAllBtn, 3, 4, 3 , 1);
        clearAllBtn.setOnAction(event -> Util.clearAll(allDisplays));

        // Create a scene with the grid pane as the root node
        Scene scene = new Scene(gridPane);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.setWidth(530);
        stage.setHeight(400);
        stage.show();
    }

    private void initializeDataSet(TextArea dataSet, TextField countField) {
        int n;
        Alert alert = new Alert(AlertType.INFORMATION);
        try {
            n = Integer.parseInt(countField.getText());
        } catch(Exception e) {
            alert.setContentText("Invalid number. It must be an integer and its range should be between 1 and 2147483647.");
            alert.showAndWait();
            return;
        }
        int[] data = DataGenerator.generate(n);
        statistics.setData(data);
        dataSet.setText(Arrays.toString(data).replace("[", "").replace("]", ""));
    }
}
