package com.dmcs.blaszkub;

import com.dmcs.blaszkub.core.ShipPlacer;
import com.dmcs.blaszkub.data.BoardData;
import com.dmcs.blaszkub.data.ShipData;
import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Coordinate;
import com.dmcs.blaszkub.model.Field;
import com.dmcs.blaszkub.model.Ship;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ShipPlacerTest {

    @Test
    public void should_return_false_when_ship_can_not_be_placed_case_1() {
        Board board_1 = BoardData.getBoard(8, 8);

        board_1.setFieldByCoordinate(new Coordinate(4, 1), FieldType.OCCUPIED_BY_SHIP);
        board_1.setFieldByCoordinate(new Coordinate(4, 2), FieldType.OCCUPIED_BY_SHIP);
        board_1.setFieldByCoordinate(new Coordinate(4, 3), FieldType.OCCUPIED_BY_SHIP);

        Coordinate ship_coordinate_1 = new Coordinate(2, 3);
        Coordinate ship_coordinate_2 = new Coordinate(3, 3);

        Field field_1 = new Field(ship_coordinate_1, FieldType.EMPTY);
        Field field_2 = new Field(ship_coordinate_2, FieldType.EMPTY);

        Ship ship_1 = ShipData.getShip(Arrays.asList(field_1, field_2));

        assertFalse(ShipPlacer.canShipBePlaced(ship_1, board_1));
    }

    @Test
    public void should_return_false_when_ship_can_not_be_placed_case_2() {
        Board board_1 = BoardData.getBoard(8, 8);

        board_1.setFieldByCoordinate(new Coordinate(0, 3), FieldType.OCCUPIED_BY_SHIP);
        board_1.setFieldByCoordinate(new Coordinate(1, 3), FieldType.OCCUPIED_BY_SHIP);
        board_1.setFieldByCoordinate(new Coordinate(2, 3), FieldType.OCCUPIED_BY_SHIP);
        board_1.setFieldByCoordinate(new Coordinate(3, 3), FieldType.OCCUPIED_BY_SHIP);

        Coordinate ship_coordinate_1 = new Coordinate(2, 0);
        Coordinate ship_coordinate_2 = new Coordinate(2, 1);
        Coordinate ship_coordinate_3 = new Coordinate(2, 2);

        Field field_1 = new Field(ship_coordinate_1, FieldType.EMPTY);
        Field field_2 = new Field(ship_coordinate_2, FieldType.EMPTY);
        Field field_3 = new Field(ship_coordinate_3, FieldType.EMPTY);

        Ship ship_1 = ShipData.getShip(Arrays.asList(field_1, field_2, field_3));

        assertFalse(ShipPlacer.canShipBePlaced(ship_1, board_1));
    }

    @Test
    public void should_return_true_when_ship_can_be_placed_case1() {
        Board board_1 = BoardData.getBoard(8, 8);

        board_1.setFieldByCoordinate(new Coordinate(0, 3), FieldType.OCCUPIED_BY_SHIP);
        board_1.setFieldByCoordinate(new Coordinate(1, 3), FieldType.OCCUPIED_BY_SHIP);
        board_1.setFieldByCoordinate(new Coordinate(2, 3), FieldType.OCCUPIED_BY_SHIP);
        board_1.setFieldByCoordinate(new Coordinate(3, 3), FieldType.OCCUPIED_BY_SHIP);

        Coordinate ship_coordinate_1 = new Coordinate(3, 0);
        Coordinate ship_coordinate_2 = new Coordinate(3, 1);

        Field field_1 = new Field(ship_coordinate_1, FieldType.EMPTY);
        Field field_2 = new Field(ship_coordinate_2, FieldType.EMPTY);

        Ship ship_1 = ShipData.getShip(Arrays.asList(field_1, field_2));

        assertTrue(ShipPlacer.canShipBePlaced(ship_1, board_1));
    }

    @Test
    public void should_return_true_when_ship_can_be_placed_case2() {
        Board board_1 = BoardData.getBoard(8, 8);

        board_1.setFieldByCoordinate(new Coordinate(0, 3), FieldType.OCCUPIED_BY_SHIP);
        board_1.setFieldByCoordinate(new Coordinate(1, 3), FieldType.OCCUPIED_BY_SHIP);
        board_1.setFieldByCoordinate(new Coordinate(2, 3), FieldType.OCCUPIED_BY_SHIP);
        board_1.setFieldByCoordinate(new Coordinate(3, 3), FieldType.OCCUPIED_BY_SHIP);

        Coordinate ship_coordinate_1 = new Coordinate(7, 5);
        Coordinate ship_coordinate_2 = new Coordinate(7, 6);
        Coordinate ship_coordinate_3 = new Coordinate(7, 7);

        Field field_1 = new Field(ship_coordinate_1, FieldType.EMPTY);
        Field field_2 = new Field(ship_coordinate_2, FieldType.EMPTY);
        Field field_3 = new Field(ship_coordinate_3, FieldType.EMPTY);

        Ship ship_1 = ShipData.getShip(Arrays.asList(field_1, field_2, field_3));

        assertTrue(ShipPlacer.canShipBePlaced(ship_1, board_1));
    }

    @Test
    public void should_return_true_when_ship_can_be_placed_case3() {
        Board board_1 = BoardData.getBoard(8, 8);

        board_1.setFieldByCoordinate(new Coordinate(0, 0), FieldType.OCCUPIED_BY_SHIP);

        Coordinate ship_coordinate_1 = new Coordinate(3, 0);
        Coordinate ship_coordinate_2 = new Coordinate(3, 1);

        Field field_1 = new Field(ship_coordinate_1, FieldType.EMPTY);
        Field field_2 = new Field(ship_coordinate_2, FieldType.EMPTY);

        Ship ship_1 = ShipData.getShip(Arrays.asList(field_1, field_2));

        assertTrue(ShipPlacer.canShipBePlaced(ship_1, board_1));
    }

    @Test
    public void place_ship_case_1() {
        Board board_1 = BoardData.getBoard(8, 8);

        Field field_1 = board_1.getFieldByCoodinates(0, 0);

        Field field_2 = board_1.getFieldByCoodinates(3, 0);
        Field field_3 = board_1.getFieldByCoodinates(3, 1);
        Field field_4 = board_1.getFieldByCoodinates(3, 2);

        Ship ship_1 = ShipData.getShip(Arrays.asList(field_1));
        Ship ship_2 = ShipData.getShip(Arrays.asList(field_2, field_3, field_4));

        ShipPlacer.placeShip(ship_1, board_1);
        ShipPlacer.placeShip(ship_2, board_1);

        assertSame(board_1.getFieldByCoodinates(0, 0).getFieldType(), FieldType.OCCUPIED_BY_SHIP);

        assertSame(board_1.getFieldByCoodinates(3, 0).getFieldType(), FieldType.OCCUPIED_BY_SHIP);
        assertSame(board_1.getFieldByCoodinates(3, 1).getFieldType(), FieldType.OCCUPIED_BY_SHIP);
        assertSame(board_1.getFieldByCoodinates(3, 2).getFieldType(), FieldType.OCCUPIED_BY_SHIP);
    }

}
