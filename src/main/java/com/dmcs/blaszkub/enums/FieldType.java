package com.dmcs.blaszkub.enums;

public enum FieldType {
    EMPTY("0"),
    SHOOTED("X"),
    SHOOTED_SHIP("#"),
    SUBMERGED_SHIP("@"),
    OCCUPIED_BY_SHIP("$");

    private final String sign;

    FieldType(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return sign;
    }
}
