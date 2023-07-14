package com.soen6611.model;

import java.util.Random;

public class DataGenerator {
    private static final int MAX = 1001;

    public static int[] generate(int n) {
        Random random = new Random(System.currentTimeMillis());
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt(MAX);
        }
        return data;
    }
}
