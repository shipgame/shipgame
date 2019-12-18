package com.dmcs.blaszkub.utils;

public class NumberGenerator {
    public static int getNumber(int min, int max) {
        return min + (int) (Math.random() * (max - min) + 1);
    }
}
