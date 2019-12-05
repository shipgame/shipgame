package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.exception.FieldNotFoundException;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Coordinate;
import com.dmcs.blaszkub.model.Field;

public class GameLogic {

    public static boolean isMoveValid(int x, int y, Board board) {
        if (!isCordInBoardRange(x, y, board)) {
            return false;
        }

        return !isFieldAlreadyShooted(board.getFieldByCoodinates(x, y).getCoordinate(), board);
    }

    public static boolean isCordInBoardRange(int x, int y, Board board) {
        try {
            board.getFieldByCoodinates(x, y);
            return true;
        } catch (FieldNotFoundException e) {
            return false;
        }
    }

    public static boolean isCordInBoardRange(Coordinate coordinate, Board board) {
        try {
            board.getFieldByCoordinate(coordinate);
            return true;
        } catch (FieldNotFoundException e) {
            return false;
        }
    }

    public static boolean isFieldAlreadyShooted(Coordinate coordinate, Board board) {
        Field field = board.getFieldByCoordinate(coordinate);
        return field.isShootedShip() || field.isShooted() || field.isSubmergedShip();
    }

    public static boolean canFieldBeMarkedAsShooted(int x, int y, Board board) {
        if (!isCordInBoardRange(x, y, board)) {
            return false;
        }

        Field field = board.getFieldByCoodinates(x, y);
        return field.isEmpty();
    }

    public static boolean canShipFieldBeSet(Coordinate coordinate, Board board) {
        int xStartPoint = coordinate.getX();
        int yStartPoint = coordinate.getY();

        for (int i = xStartPoint - 1; i <= xStartPoint + 1; i++) {
            for (int j = yStartPoint - 1; j <= yStartPoint + 1; j++) {
                Coordinate coordinateAround = new Coordinate(i, j);
                if (!GameLogic.isCordInBoardRange(coordinateAround, board)) {
                    continue;
                }

                Field fieldAround = board.getFieldByCoordinate(coordinateAround);
                if (!fieldAround.isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }
}
