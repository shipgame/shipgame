package com.dmcs.blaszkub.model;

import com.dmcs.blaszkub.enums.FieldType;
import lombok.Data;


@Data
public class Board {
    private int xAxisLength;
    private int yAxisLength;
    private Field[][] board;

    public Board(int xAxisLength, int yAxisLength) {
        this.xAxisLength = xAxisLength;
        this.yAxisLength = yAxisLength;

        this.board = new Field[xAxisLength][yAxisLength];
        initEmptyFields();
    }

    public void setField(Coordinate coordinate) {
        for (int i = 0; i < getXAxisLength(); i++) {
            for (int j = 0; j < getYAxisLength(); j++) {
                if (i == coordinate.getX() && j == coordinate.getY()) {
                    //TODO jesli na polu jest statek sprwadzaj to dajesz ship_shooted
                    Field field = board[i][j];
                    field.setFieldType(FieldType.SHOOTED);
                }
            }
        }
    }

    private void initEmptyFields() {
        for (int i = 0; i < getXAxisLength(); i++) {
            for (int j = 0; j < getYAxisLength(); j++) {
                board[i][j] = new Field(new Coordinate(i, j), FieldType.EMPTY);
            }
        }
    }

}
