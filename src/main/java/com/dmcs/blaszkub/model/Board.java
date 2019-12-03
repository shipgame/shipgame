package com.dmcs.blaszkub.model;

import com.dmcs.blaszkub.core.GameLogic;
import com.dmcs.blaszkub.enums.FieldType;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class Board {
    private int xAxisLength;
    private int yAxisLength;
    private Field[][] board;
    private List<Ship> ships;

    public Board(int xAxisLength, int yAxisLength) {
        this.xAxisLength = xAxisLength;
        this.yAxisLength = yAxisLength;
        initBoard();
    }

    public void setFieldByCoordinate(Coordinate coordinate, FieldType fieldType) {
        Field field = getFieldByCoordinate(coordinate);
        field.setFieldType(fieldType);
    }

    private void initBoard() {
        this.board = new Field[xAxisLength][yAxisLength];
        for (int i = 0; i < getXAxisLength(); i++) {
            for (int j = 0; j < getYAxisLength(); j++) {
                board[i][j] = new Field(new Coordinate(i, j), FieldType.EMPTY);
            }
        }
    }


    public Field getFieldByCoordinate(Coordinate coordinate) {
        for (int i = 0; i < getXAxisLength(); i++) {
            for (int j = 0; j < getYAxisLength(); j++) {
                if (i == coordinate.getX() && j == coordinate.getY()) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    public Field getFieldByCoodinates(int x, int y) {
        for (int i = 0; i < getXAxisLength(); i++) {
            for (int j = 0; j < getYAxisLength(); j++) {
                if (i == x && j == y) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    public Ship getShipByCoordinate(final Coordinate coordinate) {
        for (Ship ship : ships) {
            boolean match = ship.getFields().stream().anyMatch(f -> f.getCoordinate().equals(coordinate));
            if (match) {
                return ship;
            }
        }
        return null;
    }

    public void fillFieldsAroundSubmergedShip(Ship ship) {
        List<Field> fields = ship.getFields();
        List<Coordinate> coordinates = fields.stream().map(Field::getCoordinate).collect(Collectors.toList());

        for (Coordinate coordinate : coordinates) {
            int xStartPoint = coordinate.getX();
            int yStartPoint = coordinate.getY();

            for (int i = xStartPoint - 1; i <= xStartPoint + 1; i++) {
                for (int j = yStartPoint - 1; j <= yStartPoint + 1; j++) {
                    Coordinate coordinateToFill = new Coordinate(i, j);
                    if (GameLogic.canFieldBeMarkedAsShooted(coordinateToFill, this)) {
                        Field field = getFieldByCoordinate(coordinate);
                        field.setFieldType(FieldType.SHOOTED);
                    }
                }
            }

        }
    }
}
