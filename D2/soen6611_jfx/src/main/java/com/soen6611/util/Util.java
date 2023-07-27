package com.soen6611.util;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.List;

public class Util {
    public static Button createOperatorButton(String operator) {
        Button button = new Button(operator);
        button.setPrefWidth(40);
        return button;
    }

    public static void clearAll(List<Label> allDisplays) {
        for (Label lbl : allDisplays) {
            lbl.setText("0");
        }
    }
}
