package com.dmcs.blaszkub.exception;

public class FieldNotFoundException extends RuntimeException {
    private final Integer x;
    private final Integer y;

    public FieldNotFoundException(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
