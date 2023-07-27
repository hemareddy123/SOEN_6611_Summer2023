package com.soen6611.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Statistics {
    private int[] data;

    public void setData(int[] data) {
        this.data = data;
    }

    /**
     * clear data set
     */
    public void clear() {
        this.data = null;
    }

    /**
     * check if data set exists before calculation
     */
    public void checkDataExists() throws Exception {
        if (data == null || data.length == 0) {
            throw new Exception("There's no valid data.");
        }
    }

    /**
     * Return the minimum number(m) in the data set.
     * @return the minimum number(m) in the data set.
     */
    public String getMin() throws Exception {
        checkDataExists();
        int min = data[0];
        for (int e : data) {
            if (min > e) {
                min = e;
            }
        }
        return String.valueOf(min);
    }

    /**
     * Return the maximum number(M) in the data set.
     * @return the maximum number(M) in the data set.
     */
    public String getMax() throws Exception {
        checkDataExists();
        int max = data[0];
        for (int e : data) {
            if (max < e) {
                max = e;
            }
        }
        return String.valueOf(max);
    }

    /**
     * Return a list of mode o (most frequent number) in the data set.
     * @return an ArrayList which contains most frequent number(s)
     *         in the data set.
     */
    public String getMode() throws Exception {
        checkDataExists();
        ArrayList<Integer> mode = new ArrayList<>();
        HashMap<Integer, Integer> hashmap = new HashMap<>();

        for (int num : data) {
            if (hashmap.containsKey(num)) {
                hashmap.put(num, hashmap.get(num) + 1);
            }
            else {
                hashmap.put(num, 1);
            }
        }

        int maxCount = 0;
        for (int count : hashmap.values()) {
            maxCount = count > maxCount ? count : maxCount;
        }

        for (int key : hashmap.keySet()) {
            if (hashmap.get(key) == maxCount) {
                mode.add(key);
            }
        }

        return mode.toString().replace("[", "").replace("]", "");
    }

    /**
     * Return the median d of the data set.
     * @return the middle number of the data set if there are odd number of data.
     *         Otherwise, the arithmetic mean of the two middle numbers is returned.
     */
    public String getMedian() throws Exception {
        checkDataExists();
        int mid = data.length / 2;
        if (data.length % 2 == 0) {
            return String.valueOf((data[mid] + data[mid - 1]) / 2);
        }
        else {
            return String.valueOf(data[mid]);
        }
    }

    /**
     * Return arithmetic mean μ of the data set.
     * @return a real number that is the arithmetic
     *         mean of the data set.
     */
    public String getArithmeticMean() throws Exception {
        checkDataExists();
        int total = 0;
        for (int num : data) {
            total += num;
        }
        return String.valueOf(total / data.length);
    }

    /**
     * Return the mean absolute deviation(MAD) of the data set.
     * @return a real number that is the mean of absolute deviation
     *         of the data set.
     */
    public String getMeanAbsoluteDeviation() throws Exception {
        int mean = Integer.parseInt(getArithmeticMean());
        int total = 0;
        for (int num : this.data) {
            total += (abs(num - mean));
        }
        return String.valueOf(total / data.length);
    }

    /**
     * Return the standard deviation σ.
     * @return a real number that is the sample standard
     * 			deviation of the data set.
     */
    public String getStandardDeviation() throws Exception {
        int mean = Integer.parseInt(getArithmeticMean());
        int variance = 0;
        for (int num : this.data) {
            variance += ((num - mean) * (num - mean));
        }
        return String.valueOf(sqrt(variance / (data.length - 1)));
    }

    /**
     * Return the absolute value of a int value.
     * @param num that argument whose absolute value is to be determined.
     * @return the absolute value of the argument.
     */
    public static double abs(double num) {
        return num >= 0 ? num : -num;
    }

    /**
     * Return square root of the given number. Use naive estimate method to make an initial
     * guess. Our guess is the biggest integer which perfect square is smaller than the given number.
     * Then apply the initial guess on the Babylonian method.
     *
     * @param num a real number.
     * @return    the square root of the given number.
     */
    public static double sqrt(double num) {
        double tmp = 1.0;
        double guess = 1.0;
        boolean isLessThanOne = num < 1;

        // base case
        if (num == 0 || num == 1) {
            return num;
        }

        if (isLessThanOne) {
            num = 1 / num;
        }

        // naive guess to get a initial guess number as
        // closest to the correct answer as possible.
        while (guess <= num) {
            tmp++;
            guess = tmp * tmp;
        }
        tmp -= 1;
        guess = tmp * tmp;

        // Babylonian method. Accuracy is set to 0.00001
        double newGuess = (num / guess + guess) / 2;
        while (abs(newGuess - guess) > 0.00001) {
            guess = newGuess;
            newGuess = (num / guess + guess) / 2;
        }

        if (isLessThanOne) {
            return 1 / newGuess;
        }

        return newGuess;
    }

    public void calculateAndDisplay(Label display, String method) {
        String result = "";
        Alert alert = new Alert(AlertType.INFORMATION);
        try {
            switch(method) {
                case "m":
                    result = this.getMin();
                    break;
                case "M":
                    result = this.getMax();
                    break;
                case "o":
                    result = this.getMode();
                    break;
                case "d":
                    result = this.getMedian();
                    break;
                case "μ":
                    result = this.getArithmeticMean();
                    break;
                case "MAD":
                    result = this.getMeanAbsoluteDeviation();
                    break;
                case "σ":
                    result = this.getStandardDeviation();
                    break;
            }
            display.setText(result);
        } catch (Exception e) {
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void calculateAll(List<Label> allDisplays) {
        String result;
        Alert alert = new Alert(AlertType.INFORMATION);
        try {
            result = this.getMin();
            allDisplays.get(0).setText(result);
            result = this.getMax();
            allDisplays.get(1).setText(result);
            result = this.getMode();
            allDisplays.get(2).setText(result);
            result = this.getMedian();
            allDisplays.get(3).setText(result);
            result = this.getArithmeticMean();
            allDisplays.get(4).setText(result);
            result = this.getMeanAbsoluteDeviation();
            allDisplays.get(5).setText(result);
            result = this.getStandardDeviation();
            allDisplays.get(6).setText(result);
        } catch (Exception e) {
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
