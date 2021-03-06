package com.dmcs.blaszkub.model;

import com.dmcs.blaszkub.core.GameLogic;
import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.exception.FieldNotFoundException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class Board {
    private int xAxisLength;
    private int yAxisLength;
    private Field[][] fields;
    private List<Ship> ships = new ArrayList<>();

    public Board(int xAxisLength, int yAxisLength) {
        this.xAxisLength = xAxisLength;
        this.yAxisLength = yAxisLength;
        initBoard();
    }

    public void setFieldByCoordinate(Coordinate coordinate, FieldType fieldType) {
        Field field = getFieldByCoordinate(coordinate);
        field.setFieldType(fieldType);
    }


    public Field getFieldByCoordinate(Coordinate coordinate) {
        for (int i = 0; i < getXAxisLength(); i++) {
            for (int j = 0; j < getYAxisLength(); j++) {
                if (i == coordinate.getX() && j == coordinate.getY()) {
                    return fields[i][j];
                }
            }
        }
        throw new FieldNotFoundException();
    }

    public Field getFieldByCoordinates(int x, int y) {
        for (int i = 0; i < getXAxisLength(); i++) {
            for (int j = 0; j < getYAxisLength(); j++) {
                if (i == x && j == y) {
                    return fields[i][j];
                }
            }
        }
        throw new FieldNotFoundException();
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
        List<Field> fieldsOccupiedByShip = ship.getFields();
        List<Coordinate> coordinates = fieldsOccupiedByShip.stream().map(Field::getCoordinate).collect(Collectors.toList());

        for (Coordinate coordinate : coordinates) {
            int xStartPoint = coordinate.getX();
            int yStartPoint = coordinate.getY();

            for (int i = xStartPoint - 1; i <= xStartPoint + 1; i++) {
                for (int j = yStartPoint - 1; j <= yStartPoint + 1; j++) {
                    Coordinate coordinateToFill = new Coordinate(i, j);

                    if (GameLogic.canFieldBeMarkedAsShooted(coordinateToFill.getX(), coordinateToFill.getY(), this)) {
                        Field field = getFieldByCoordinates(coordinateToFill.getX(), coordinateToFill.getY());
                        field.setFieldType(FieldType.SHOOTED);
                    }
                }
            }

        }
    }

    public void addShip(Ship ship) {
        getShips().add(ship);
    }

    public void removeAllShips() {
        getShips().clear();
    }

    private void initBoard() {
        this.fields = new Field[xAxisLength][yAxisLength];
        for (int i = 0; i < getXAxisLength(); i++) {
            for (int j = 0; j < getYAxisLength(); j++) {
                fields[i][j] = new Field(new Coordinate(i, j), FieldType.EMPTY);
            }
        }
    }
}
