package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.config.Constants;
import com.dmcs.blaszkub.enums.DirectionType;
import com.dmcs.blaszkub.enums.ShipType;
import com.dmcs.blaszkub.exception.AutomaticPlacingShipsException;
import com.dmcs.blaszkub.exception.FieldNotFoundException;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Coordinate;
import com.dmcs.blaszkub.model.Field;
import com.dmcs.blaszkub.model.Ship;
import com.dmcs.blaszkub.utils.NumberGenerator;
import com.dmcs.blaszkub.utils.RandomEnum;
import com.dmcs.blaszkub.utils.TimeCounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutomaticShipPlacer {

    private static final String COULDN_T_AUTO_SET_SHIPS_CHECK_FOR_BOARD_SIZE_OR_SHIP_CONFIG = "Couldn't auto set ships, check for board size or ship config";
    private static final String COULDN_T_AUTO_SET_SHIPS_CHECK_FOR_BOARD_SIZE_OR_SHIP_CONFIG1 = "Couldn't auto set ships, check for board size or ship config";

    public void placeShips(List<ShipType> shipTypes, Board board) {
        TimeCounter generatingFieldsTimer = new TimeCounter();
        TimeCounter placingShipTimer = new TimeCounter();

        for (ShipType shipType : shipTypes) {

            Coordinate startingCoordinate = new Coordinate(0, 0);

            Ship ship = new Ship();

            placingShipTimer.start();
            do {
                setStartingPoint(startingCoordinate, board);

                DirectionType direction = RandomEnum.randomEnum(DirectionType.class);
                List<Field> fields;

                generatingFieldsTimer.start();
                do {
                    fields = getShipFields(direction, shipType, board, startingCoordinate);
                    if (generatingFieldsTimer.getTimeSpentInMilis() >= Constants.MAX_AUTOMATIC_PLACING_SHIPS_TIME_IN_SECONDS) {
                        board.removeAllShips();
                        throw new AutomaticPlacingShipsException(COULDN_T_AUTO_SET_SHIPS_CHECK_FOR_BOARD_SIZE_OR_SHIP_CONFIG);
                    }
                } while (fields.isEmpty());


                ship.setFields(fields);
                ship.setShipType(shipType);

                if (placingShipTimer.getTimeSpentInMilis() >= Constants.MAX_AUTOMATIC_PLACING_SHIPS_TIME_IN_SECONDS) {
                    board.removeAllShips();
                    throw new AutomaticPlacingShipsException(COULDN_T_AUTO_SET_SHIPS_CHECK_FOR_BOARD_SIZE_OR_SHIP_CONFIG1);
                }
            } while (!ShipPlacer.canShipBePlaced(ship, board));

            ShipPlacer.placeShip(ship, board);
        }
    }

    private void setStartingPoint(Coordinate coordinate, Board board) {
        int x;
        int y;
        do {
            x = NumberGenerator.getNumber(0, board.getXAxisLength() - 1);
            y = NumberGenerator.getNumber(0, board.getYAxisLength() - 1);
            coordinate.setX(x);
            coordinate.setY(y);
        } while (!GameLogic.isCordInBoardRange(x, y, board));
    }

    private List<Field> getShipFields(DirectionType directionType, ShipType shipType, Board board, Coordinate startingPoint) {
        List<Field> fields = new ArrayList<>();

        int startingX = startingPoint.getX();
        int startingY = startingPoint.getY();

        Field startingFieldOfShip = board.getFieldByCoordinates(startingX, startingY);
        fields.add(startingFieldOfShip);

        try {
            for (int i = 1; i < ShipType.getNumberOfFieldsOccupied(shipType); i++) {
                if (directionType.equals(DirectionType.LEFT)) {
                    Field field = board.getFieldByCoordinates(startingX, startingY - i);
                    fields.add(field);
                } else if (directionType.equals(DirectionType.TOP)) {
                    Field field = board.getFieldByCoordinates(startingX + i, startingY);
                    fields.add(field);
                } else if (directionType.equals(DirectionType.RIGHT)) {
                    Field field = board.getFieldByCoordinates(startingX, startingY + i);
                    fields.add(field);
                } else if (directionType.equals(DirectionType.DOWN)) {
                    Field field = board.getFieldByCoordinates(startingX - i, startingY);
                    fields.add(field);
                } else {
                    return Collections.emptyList();
                }
            }

            return fields;
        } catch (FieldNotFoundException e) {
            return Collections.emptyList();
        }
    }
}
