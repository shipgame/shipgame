package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.config.GameConfig;
import com.dmcs.blaszkub.core.abstraction.IGame;
import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Coordinate;
import com.dmcs.blaszkub.model.Field;
import com.dmcs.blaszkub.model.Ship;
import com.dmcs.blaszkub.utils.BoardPrinter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game implements IGame {

    private GameConfig gameConfig;
    private boolean isFinished;
    private Board board;

    public Game(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        initBoard();
    }

    public void move(Coordinate coordinate) {
        Field field = board.getFieldByCoordinate(coordinate);

        if (field.isEmpty()) {
            board.setField(coordinate, FieldType.SHOOTED_SHIP);
        } else if (field.isOccupiedByShip()) {
            board.setField(coordinate, FieldType.SHOOTED_SHIP);
            Ship ship = board.getShipByCoordinate(coordinate);
            if (ship.areAllFieldsShipShooted()) {
                ship.setShipAsSubmerged();
                board.fillFieldsAroundSubmergedShip(ship);
            }
        }
        //TODO jesli field jest pusty -> daj na shooted
        //jesli field jset zajety przez statek -> wyciagnij shipa po koordynatach
        //zmien pole na shipshooted, jesli dla tego statku wszystkie fieldy sa jako shipShooted
        //to dookola daj na shooted pola i zmien status wszystkich pol na zatopiony
    }

    public void start() {
        //TODO wyciagnij z gameConfig rozmiar planszy -> initBoard
        //wyciagnij liczbe statkow i je rozmiesc, jak automat to losuj
        //jak manual podajac kordy statku ustawiaj go

        BoardPrinter.print(board);
    }

    private void initBoard() {
        this.board = new Board(gameConfig.getXAxisLength(), gameConfig.getYAxisLength());
    }
}
