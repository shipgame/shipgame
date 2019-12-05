package com.dmcs.blaszkub.utils;

import java.util.Random;

public class RandomEnum {

    private static final Random random = new Random();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
