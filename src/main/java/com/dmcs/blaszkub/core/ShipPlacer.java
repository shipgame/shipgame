package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.enums.ShipType;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Coordinate;
import com.dmcs.blaszkub.model.Field;
import com.dmcs.blaszkub.model.Ship;

import java.util.ArrayList;
import java.util.List;

public class ShipPlacer {

    public static boolean canShipBePlaced(Ship ship, Board board) {
        List<Field> fields = ship.getFields();
        return fields.stream().allMatch(f -> GameLogic.canShipFieldBeSet(f.getCoordinate(), board));
    }

    public static void placeShip(Ship ship, Board board) {
        List<Field> fields = ship.getFields();

        for (Field field : fields) {
            board.setFieldByCoordinate(field.getCoordinate(), FieldType.OCCUPIED_BY_SHIP);
        }

        board.addShip(ship);
    }

    static List<Field> getShipFields(ShipType shipType) {
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < ShipType.getNumberOfFieldsOccupied(shipType); i++) {
            fields.add(setField());
        }

        return fields;
    }

    private static Field setField() {
        Coordinate coordinate = Coordinate.getCoordinatesFromPlayer();

        return new Field(coordinate, FieldType.OCCUPIED_BY_SHIP);
    }
}
