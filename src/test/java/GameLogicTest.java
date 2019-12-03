import com.dmcs.blaszkub.core.GameLogic;
import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GameLogicTest {

    @Test
    public void should_return_false_when_coordinates_are_not_in_board_range() {
        Board board = getBoard(8, 8);

        Coordinate coordinate_1 = new Coordinate(-1, 5);
        Coordinate coordinate_2 = new Coordinate(2, 10);
        Coordinate coordinate_3 = new Coordinate(8, 8);
        Coordinate coordinate_4 = new Coordinate(32, 33);
        Coordinate coordinate_5 = new Coordinate(-2, -10);

        assertFalse(GameLogic.isMoveValid(coordinate_1, board));
        assertFalse(GameLogic.isMoveValid(coordinate_2, board));
        assertFalse(GameLogic.isMoveValid(coordinate_3, board));
        assertFalse(GameLogic.isMoveValid(coordinate_4, board));
        assertFalse(GameLogic.isMoveValid(coordinate_5, board));
    }

    @Test
    public void should_return_false_when_coordinates_are_already_shooted() {
        Board board_1 = getBoard(8, 8);
        Board board_2 = getBoard(8, 8);

        board_1.setField(new Coordinate(4, 4), FieldType.SHOOTED);
        board_2.setField(new Coordinate(0, 1), FieldType.SHOOTED_SHIP);

        Coordinate coordinate_1 = new Coordinate(4, 4);
        Coordinate coordinate_2 = new Coordinate(0, 1);

        assertFalse(GameLogic.isMoveValid(coordinate_1, board_1));
        assertFalse(GameLogic.isMoveValid(coordinate_2, board_2));
    }

    @Test
    public void should_return_true_when_move_is_valid() {
        Board board_1 = getBoard(8, 8);
        Board board_2 = getBoard(8, 8);
        Board board_3 = getBoard(8, 8);

        board_1.setField(new Coordinate(4, 4), FieldType.SHOOTED);
        board_2.setField(new Coordinate(3, 3), FieldType.SHOOTED_SHIP);

        Coordinate coordinate_1 = new Coordinate(1, 1);
        Coordinate coordinate_2 = new Coordinate(2, 2);
        Coordinate coordinate_3 = new Coordinate(0, 0);

        assertTrue(GameLogic.isMoveValid(coordinate_1, board_1));
        assertTrue(GameLogic.isMoveValid(coordinate_2, board_2));
        assertTrue(GameLogic.isMoveValid(coordinate_3, board_3));
    }

    @Test
    public void should_return_false_when_field_can_not_be_marked_as_shooted_when_coordinate_is_not_in_board_range() {
        Board board_1 = getBoard(4, 4);

        Coordinate coordinate_1 = new Coordinate(-1, 2);
        Coordinate coordinate_2 = new Coordinate(2, 5);
        Coordinate coordinate_3 = new Coordinate(4, 4);
        Coordinate coordinate_4 = new Coordinate(-2, -3);
        Coordinate coordinate_5 = new Coordinate(6, 6);

        assertFalse(GameLogic.canFieldBeMarkedAsShooted(coordinate_1, board_1));
        assertFalse(GameLogic.canFieldBeMarkedAsShooted(coordinate_2, board_1));
        assertFalse(GameLogic.canFieldBeMarkedAsShooted(coordinate_3, board_1));
        assertFalse(GameLogic.canFieldBeMarkedAsShooted(coordinate_4, board_1));
        assertFalse(GameLogic.canFieldBeMarkedAsShooted(coordinate_5, board_1));
    }

    @Test
    public void should_return_false_when_field_can_not_be_marked_as_shooted_when_field_is_not_empty() {
        Board board_1 = getBoard(4, 4);

        board_1.setField(new Coordinate(1, 1), FieldType.SUBMERGED_SHIP);
        board_1.setField(new Coordinate(2, 3), FieldType.SUBMERGED_SHIP);

        Coordinate coordinate_1 = new Coordinate(1, 1);
        Coordinate coordinate_2 = new Coordinate(2, 3);

        assertFalse(GameLogic.canFieldBeMarkedAsShooted(coordinate_1, board_1));
        assertFalse(GameLogic.canFieldBeMarkedAsShooted(coordinate_2, board_1));
    }

    /* 1 field ship case */
    @Test
    public void should_return_true_when_field_can_be_marked_as_shooted() {
        Board board_1 = getBoard(8, 8);

        board_1.setField(new Coordinate(5, 5), FieldType.SUBMERGED_SHIP);

        Coordinate coordinate_1 = new Coordinate(4, 4);
        Coordinate coordinate_2 = new Coordinate(4, 5);
        Coordinate coordinate_3 = new Coordinate(4, 6);
        Coordinate coordinate_4 = new Coordinate(5, 4);
        Coordinate coordinate_5 = new Coordinate(5, 6);
        Coordinate coordinate_6 = new Coordinate(6, 4);
        Coordinate coordinate_7 = new Coordinate(6, 5);
        Coordinate coordinate_8 = new Coordinate(6, 6);

        assertTrue(GameLogic.canFieldBeMarkedAsShooted(coordinate_1, board_1));
        assertTrue(GameLogic.canFieldBeMarkedAsShooted(coordinate_2, board_1));
        assertTrue(GameLogic.canFieldBeMarkedAsShooted(coordinate_3, board_1));
        assertTrue(GameLogic.canFieldBeMarkedAsShooted(coordinate_4, board_1));

        assertTrue(GameLogic.canFieldBeMarkedAsShooted(coordinate_5, board_1));
        assertTrue(GameLogic.canFieldBeMarkedAsShooted(coordinate_6, board_1));
        assertTrue(GameLogic.canFieldBeMarkedAsShooted(coordinate_7, board_1));
        assertTrue(GameLogic.canFieldBeMarkedAsShooted(coordinate_8, board_1));
    }

    @Test
    public void should_return_false_when_shipfields_can_not_be_set_around_ship_touching_wall() {
        Board board_1 = getBoard(8, 8);

        board_1.setField(new Coordinate(0, 0), FieldType.OCCUPIED_BY_SHIP);

        Coordinate ship_coordinate_1 = new Coordinate(-1, -1);
        Coordinate ship_coordinate_2 = new Coordinate(-1, 0);
        Coordinate ship_coordinate_3 = new Coordinate(-1, 1);
        Coordinate ship_coordinate_4 = new Coordinate(0, -1);

        Coordinate ship_coordinate_5 = new Coordinate(0, 1);
        Coordinate ship_coordinate_6 = new Coordinate(1, -1);
        Coordinate ship_coordinate_7 = new Coordinate(1, 0);
        Coordinate ship_coordinate_8 = new Coordinate(1, 1);

        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_1, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_2, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_3, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_4, board_1));

        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_5, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_6, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_7, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_8, board_1));
    }

    @Test
    public void should_return_false_when_shipfields_can_not_be_set_around_ship_on_the_center_of_board() {
        Board board_1 = getBoard(8, 8);

        board_1.setField(new Coordinate(4, 1), FieldType.OCCUPIED_BY_SHIP);
        board_1.setField(new Coordinate(4, 2), FieldType.OCCUPIED_BY_SHIP);
        board_1.setField(new Coordinate(4, 3), FieldType.OCCUPIED_BY_SHIP);


        Coordinate ship_coordinate_2 = new Coordinate(4, 0);

        Coordinate ship_coordinate_3 = new Coordinate(3, 0);
        Coordinate ship_coordinate_4 = new Coordinate(3, 1);
        Coordinate ship_coordinate_5 = new Coordinate(3, 2);
        Coordinate ship_coordinate_6 = new Coordinate(3, 3);
        Coordinate ship_coordinate_7 = new Coordinate(3, 4);

        Coordinate ship_coordinate_8 = new Coordinate(4, 4);

        Coordinate ship_coordinate_9 = new Coordinate(5, 0);
        Coordinate ship_coordinate_10 = new Coordinate(5, 1);
        Coordinate ship_coordinate_11 = new Coordinate(5, 2);
        Coordinate ship_coordinate_12 = new Coordinate(5, 3);
        Coordinate ship_coordinate_13 = new Coordinate(5, 4);


        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_2, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_3, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_4, board_1));

        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_5, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_6, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_7, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_8, board_1));

        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_9, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_10, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_11, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_12, board_1));
        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_13, board_1));
    }

    @Test
    public void should_return_false_when_shipfields_can_not_be_inside_other_ship() {
        Board board_1 = getBoard(8, 8);

        board_1.setField(new Coordinate(4, 4), FieldType.OCCUPIED_BY_SHIP);

        Coordinate ship_coordinate_1 = new Coordinate(4, 4);

        assertFalse(GameLogic.canShipFieldBeSet(ship_coordinate_1, board_1));
    }

    @Test
    public void should_return_true_when_shipfield_can_be_set() {
        Board board_1 = getBoard(8, 8);

        board_1.setField(new Coordinate(0, 0), FieldType.OCCUPIED_BY_SHIP);

        board_1.setField(new Coordinate(4, 1), FieldType.OCCUPIED_BY_SHIP);
        board_1.setField(new Coordinate(4, 2), FieldType.OCCUPIED_BY_SHIP);
        board_1.setField(new Coordinate(4, 3), FieldType.OCCUPIED_BY_SHIP);

        Coordinate ship_coordinate_1 = new Coordinate(0, 3);
        Coordinate ship_coordinate_2 = new Coordinate(4, 5);
        Coordinate ship_coordinate_3 = new Coordinate(6, 1);
        Coordinate ship_coordinate_4 = new Coordinate(5, 7);


        assertTrue(GameLogic.canShipFieldBeSet(ship_coordinate_1, board_1));
        assertTrue(GameLogic.canShipFieldBeSet(ship_coordinate_2, board_1));
        assertTrue(GameLogic.canShipFieldBeSet(ship_coordinate_3, board_1));
        assertTrue(GameLogic.canShipFieldBeSet(ship_coordinate_4, board_1));
    }

    private Board getBoard(int xAxisLength, int yAxisLength) {
        return new Board(xAxisLength, yAxisLength);
    }
}
