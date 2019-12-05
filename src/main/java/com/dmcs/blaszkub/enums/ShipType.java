package com.dmcs.blaszkub.enums;

public enum ShipType {
    ONE, TWO, THREE, FOUR;

    public int getNumberOfFieldsOccupied(ShipType shipType) {
        switch (shipType) {
            case ONE:
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            default:
                return 1;
        }
    }
}
