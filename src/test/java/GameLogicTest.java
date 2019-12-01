import com.dmcs.blaszkub.core.GameLogic;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameLogicTest {

    @Test
    public void should_return_false_when_coordinates_are_not_in_board_range() {
        Board board = getBoard(8, 8);

        Coordinate coordinate_1 = new Coordinate(-1, 5);
        Coordinate coordinate_2 = new Coordinate(0, 8);
        Coordinate coordinate_3 = new Coordinate(8, 8);
        Coordinate coordinate_4 = new Coordinate(20, 30);

        assertFalse(GameLogic.isMoveValid(coordinate_1, board));
        assertFalse(GameLogic.isMoveValid(coordinate_2, board));
        assertFalse(GameLogic.isMoveValid(coordinate_3, board));
        assertFalse(GameLogic.isMoveValid(coordinate_4, board));
    }

    @Test
    public void should_return_false_when_coordinates_are_already_shooted() {
        Board board_1 = getBoard(8, 8);
        Board board_2 = getBoard(8, 8);

        board_1.setField(new Coordinate(4, 4));
        board_2.setField(new Coordinate(0, 1));

        Coordinate coordinate_1 = new Coordinate(4, 4);
        Coordinate coordinate_2 = new Coordinate(0, 1);

        assertFalse(GameLogic.isMoveValid(coordinate_1, board_1));
        assertFalse(GameLogic.isMoveValid(coordinate_2, board_2));
    }

    private Board getBoard(int xAxisLength, int yAxisLength) {
        return new Board(xAxisLength, yAxisLength);
    }
}
