package com.dmcs.blaszkub.enums;

public enum ShipType {
    ONE, TWO, THREE, FOUR;

    public static int getNumberOfFieldsOccupied(ShipType shipType) {
        switch (shipType) {
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case ONE:
            default:
                return 1;
        }
    }

    public static ShipType fromString(String ship) {
        switch (ship) {
            case "2":
                return TWO;
            case "3":
                return THREE;
            case "4":
                return FOUR;
            case "1":
            default:
                return ONE;
        }
    }
}
