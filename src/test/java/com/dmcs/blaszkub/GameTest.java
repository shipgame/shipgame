package com.dmcs.blaszkub;

import com.dmcs.blaszkub.config.GameConfig;
import com.dmcs.blaszkub.core.Game;
import com.dmcs.blaszkub.core.Player;
import com.dmcs.blaszkub.core.ShipPlacer;
import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.enums.ModeType;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Ship;
import com.dmcs.blaszkub.model.Statistics;
import com.dmcs.blaszkub.utils.BoardPrinter;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void move_test1() {
        Game game = new Game(new GameConfig(ModeType.AUTO, 8, 8), new Player(new Statistics()));

        Board board = game.getBoard();

        board.setFieldByCoordinate(board.getFieldByCoordinates(4, 1).getCoordinate(), FieldType.OCCUPIED_BY_SHIP);
        board.setFieldByCoordinate(board.getFieldByCoordinates(4, 2).getCoordinate(), FieldType.OCCUPIED_BY_SHIP);
        board.setFieldByCoordinate(board.getFieldByCoordinates(4, 3).getCoordinate(), FieldType.OCCUPIED_BY_SHIP);

        setShips_case1(game);

        game.getPlayer().makeMove(0, 0, game.getBoard());
        game.getPlayer().makeMove(0, 1, game.getBoard());
        game.getPlayer().makeMove(0, 2, game.getBoard());

        game.getPlayer().makeMove(4, 1, game.getBoard());
        game.getPlayer().makeMove(4, 2, game.getBoard());
        game.getPlayer().makeMove(4, 3, game.getBoard());

        BoardPrinter.print(game.getBoard());
    }


    private void setShips_case1(Game game) {
        Ship ship_1 = new Ship();

        Board board = game.getBoard();

        ship_1.addField(board.getFieldByCoordinates(4, 1));
        ship_1.addField(board.getFieldByCoordinates(4, 2));
        ship_1.addField(board.getFieldByCoordinates(4, 3));

        ShipPlacer.placeShip(ship_1, board);
    }
}
