package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.core.abstraction.IPlayer;
import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Field;
import com.dmcs.blaszkub.model.Ship;


public class Player implements IPlayer {

    @Override
    public void makeMove(int x, int y, Board board) {
        Field field = board.getFieldByCoodinates(x, y);
        if (field.isEmpty()) {
            board.setFieldByCoordinate(field.getCoordinate(), FieldType.SHOOTED);
        } else if (field.isOccupiedByShip()) {
            board.setFieldByCoordinate(field.getCoordinate(), FieldType.SHOOTED_SHIP);
            Ship ship = board.getShipByCoordinate(field.getCoordinate());
            if (ship.areAllFieldsShipShooted()) {
                ship.setShipAsSubmerged();
                board.fillFieldsAroundSubmergedShip(ship);
            }
        }
    }
}
