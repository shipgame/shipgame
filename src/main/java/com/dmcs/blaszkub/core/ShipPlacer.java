package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Field;
import com.dmcs.blaszkub.model.Ship;

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
}
