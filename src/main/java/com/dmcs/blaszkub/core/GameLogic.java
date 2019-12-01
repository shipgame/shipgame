package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Coordinate;
import com.dmcs.blaszkub.model.Field;

public class GameLogic {

    public static boolean isMoveValid(Coordinate coordinate, Board board) {
        if (!isCordInBoardRange(coordinate.getX(), board.getXAxisLength() - 1) || !isCordInBoardRange(coordinate.getY(), board.getYAxisLength() - 1)) {
            return false;
        }

        return !isFieldAlreadyShooted(coordinate, board);
    }

    private static boolean isCordInBoardRange(int cord, int boardLimit) {
        return cord >= 0 && cord <= boardLimit;
    }

    private static boolean isFieldAlreadyShooted(Coordinate coordinate, Board board) {
        for (int i = 0; i < board.getXAxisLength(); i++) {
            for (int j = 0; j < board.getYAxisLength(); j++) {
                Field field = board.getBoard()[i][j];
                if (!field.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }
}
