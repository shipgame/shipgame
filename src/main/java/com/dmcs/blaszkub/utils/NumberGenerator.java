package com.dmcs.blaszkub.utils;

import java.util.Random;

public class NumberGenerator {

    private NumberGenerator() {
    }

    public static int getNumber(int min, int max) {
        return min + (new Random().nextInt() * (max - min) + 1);
    }
}
