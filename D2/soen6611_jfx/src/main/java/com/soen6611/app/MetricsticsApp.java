package com.soen6611.app;

import com.soen6611.model.DataGenerator;
import com.soen6611.model.Statistics;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class MetricsticsApp extends Application {

    private Statistics statistics;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
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

        Button generateBtn = createOperatorButton("Generate Data");
        generateBtn.setPrefWidth(100);
        generateBtn.setOnAction(event -> initializeDataSet(dataSet));
        gridPane.add(generateBtn, 0, 0, 7, 1);

        Button minBtn = createOperatorButton("m"); // min
        gridPane.add(minBtn, 0, 2);
        Label minRes = new Label("0");
        gridPane.add(minRes, 0, 3);
        minBtn.setOnAction(event -> calculateAndDisplay(minRes, "m"));

        Button maxBtn = createOperatorButton("M"); // max
        gridPane.add(maxBtn, 1, 2);
        Label maxRes = new Label("0");
        gridPane.add(maxRes, 1, 3);
        maxBtn.setOnAction(event -> calculateAndDisplay(maxRes, "M"));

        Button modeBtn = createOperatorButton("o"); // mode
        gridPane.add(modeBtn, 2, 2);
        Label modeRes = new Label("0");
        gridPane.add(modeRes, 2, 3);
        modeBtn.setOnAction(event -> calculateAndDisplay(modeRes, "o"));

        Button medianBtn = createOperatorButton("d"); // median
        gridPane.add(medianBtn, 3, 2);
        Label medianRes = new Label("0");
        gridPane.add(medianRes, 3, 3);
        medianBtn.setOnAction(event -> calculateAndDisplay(medianRes, "d"));

        Button arithmeticMeanBtn = createOperatorButton("μ"); // arithmetic mean
        gridPane.add(arithmeticMeanBtn, 4, 2);
        Label arithmeticMeanRes = new Label("0");
        gridPane.add(arithmeticMeanRes, 4, 3);
        arithmeticMeanBtn.setOnAction(event -> calculateAndDisplay(arithmeticMeanRes, "μ"));

        Button meanAbsoluteDeviationBtn = createOperatorButton("MAD"); // mean absolute deviation
        meanAbsoluteDeviationBtn.setPrefWidth(50);
        gridPane.add(meanAbsoluteDeviationBtn, 5, 2);
        Label meanAbsoluteDeviationRes = new Label("0");
        gridPane.add(meanAbsoluteDeviationRes, 5, 3);
        meanAbsoluteDeviationBtn.setOnAction(event -> calculateAndDisplay(meanAbsoluteDeviationRes, "MAD"));

        Button standardDeviationBtn = createOperatorButton("σ"); // standard deviation
        gridPane.add(standardDeviationBtn, 6, 2);
        Label standardDeviationRes = new Label("0");
        gridPane.add(standardDeviationRes, 6, 3);
        standardDeviationBtn.setOnAction(event -> calculateAndDisplay(standardDeviationRes, "σ"));

        List<Label> allDisplays = Arrays.asList(minRes, maxRes, modeRes, medianRes, arithmeticMeanRes, meanAbsoluteDeviationRes, standardDeviationRes);

        Button calculateAllBtn = createOperatorButton("Calculate All"); // calculate all
        calculateAllBtn.setPrefWidth(100);
        gridPane.add(calculateAllBtn, 0, 4, 3 , 1);
        calculateAllBtn.setOnAction(event -> calculateAll(allDisplays));

        Button clearAllBtn = createOperatorButton("Clear All"); // calculate all
        clearAllBtn.setPrefWidth(100);
        gridPane.add(clearAllBtn, 3, 4, 3 , 1);
        clearAllBtn.setOnAction(event -> clearAll(allDisplays));

        // Create a scene with the grid pane as the root node
        Scene scene = new Scene(gridPane);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.setWidth(530);
        stage.setHeight(400);
        stage.show();
    }

    private void initializeDataSet(TextArea dataSet) {
        int[] data = DataGenerator.generate(1000);
        statistics.setData(data);
        dataSet.setText(Arrays.toString(data).replace("[", "").replace("]", ""));
    }

    private void calculateAndDisplay(Label display, String method) {
        String result = "";
        try {
            switch(method) {
                case "m":
                    result = statistics.getMin();
                    break;
                case "M":
                    result = statistics.getMax();
                    break;
                case "o":
                    result = statistics.getMode();
                    break;
                case "d":
                    result = statistics.getMedian();
                    break;
                case "μ":
                    result = statistics.getArithmeticMean();
                    break;
                case "MAD":
                    result = statistics.getMeanAbsoluteDeviation();
                    break;
                case "σ":
                    result = statistics.getStandardDeviation();
                    break;
            }
            display.setText(result);
        } catch (Exception e) {
            display.setText(e.getMessage());
        }
    }

    private void calculateAll(List<Label> allDisplays) {
        String result;
        try {
            result = statistics.getMin();
            allDisplays.get(0).setText(result);
            result = statistics.getMax();
            allDisplays.get(1).setText(result);
            result = statistics.getMode();
            allDisplays.get(2).setText(result);
            result = statistics.getMedian();
            allDisplays.get(3).setText(result);
            result = statistics.getArithmeticMean();
            allDisplays.get(4).setText(result);
            result = statistics.getMeanAbsoluteDeviation();
            allDisplays.get(5).setText(result);
            result = statistics.getStandardDeviation();
            allDisplays.get(6).setText(result);
        } catch (Exception e) {

        }
    }

    private Button createOperatorButton(String operator) {
        Button button = new Button(operator);
        button.setPrefWidth(40);
        return button;
    }

    private void clearAll(List<Label> allDisplays) {
        for (Label lbl : allDisplays) {
            lbl.setText("0");
        }
    }
}
