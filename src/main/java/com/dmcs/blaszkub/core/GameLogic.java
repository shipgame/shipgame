package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Coordinate;
import com.dmcs.blaszkub.model.Field;

public class GameLogic {

    public static boolean isMoveValid(Coordinate coordinate, Board board) {
        if (!isCordInBoardRange(coordinate, board)) {
            return false;
        }

        return !isFieldAlreadyShooted(coordinate, board);
    }

    public static boolean isCordInBoardRange(Coordinate coordinate, Board board) {
        return ((coordinate.getX() >= 0 && coordinate.getX() <= board.getXAxisLength() - 1) && (coordinate.getY() >= 0 && coordinate.getY() <= board.getYAxisLength() - 1));
    }

    public static boolean isFieldAlreadyShooted(Coordinate coordinate, Board board) {
        Field field = board.getFieldByCoordinate(coordinate);
        return !field.isEmpty();
    }

    public static boolean canFieldBeMarkedAsShooted(Coordinate coordinate, Board board) {
        if (!isCordInBoardRange(coordinate, board)) {
            return false;
        }

        Field field = board.getFieldByCoordinate(coordinate);
        return field.isEmpty();
    }
}
