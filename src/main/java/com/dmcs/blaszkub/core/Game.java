package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.config.CustomSettings;
import com.dmcs.blaszkub.config.GameConfig;
import com.dmcs.blaszkub.core.abstraction.IGame;
import com.dmcs.blaszkub.core.abstraction.IPlayer;
import com.dmcs.blaszkub.enums.ModeType;
import com.dmcs.blaszkub.enums.ShipType;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Coordinate;
import com.dmcs.blaszkub.model.Ship;
import com.dmcs.blaszkub.utils.BoardPrinter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.dmcs.blaszkub.config.CustomSettings.CHOOSE_SHOOT_COORDINATES;
import static com.dmcs.blaszkub.config.CustomSettings.YOU_WON;

@Getter
@Setter
public class Game implements IGame {

    private Board board;
    private final GameConfig gameConfig;
    private final IPlayer player;

    private boolean isFinished;

    public Game(GameConfig gameConfig, IPlayer player) {
        this.gameConfig = gameConfig;
        this.player = player;
        initBoard();
    }

    public void start() {
        board = new Board(gameConfig.getXAxisLength(), gameConfig.getYAxisLength());
        placeShips();
        BoardPrinter.print(board);

        while (!isGameFinished()) {
            System.out.println(CHOOSE_SHOOT_COORDINATES);
            Coordinate coordinate = Coordinate.getCoordinatesFromPlayer();
            player.makeMove(coordinate.getX(), coordinate.getY(), board);
            BoardPrinter.print(board);
        }

        System.out.println(YOU_WON);
        player.printPlayerStatistics();
    }

    private void initBoard() {
        this.board = new Board(gameConfig.getXAxisLength(), gameConfig.getYAxisLength());
    }

    private boolean isGameFinished() {
        return getBoard().getShips().stream().allMatch(Ship::isSubmerged);
    }

    private void placeShips() {
        CustomSettings customSettings = new CustomSettings();
        List<ShipType> shipTypes = customSettings.getShipTypes();

        if (gameConfig.getModeType() == ModeType.AUTO) {
            AutomaticShipPlacer automaticShipPlacer = new AutomaticShipPlacer();
            automaticShipPlacer.placeShips(shipTypes, board);
        } else {
            for (ShipType shipType : shipTypes) {
                Ship ship = new Ship(shipType, ShipPlacer.getShipFields(shipType));
                ShipPlacer.placeShip(ship, board);
            }
        }
    }
}
