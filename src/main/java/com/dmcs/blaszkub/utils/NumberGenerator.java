package com.dmcs.blaszkub.utils;

import java.util.Random;

public class NumberGenerator {
    private static final Random random = new Random();

    public static int getNumber(int min, int max) {
        return min + (int) (Math.random() * (max - min) + 1);
    }
}
