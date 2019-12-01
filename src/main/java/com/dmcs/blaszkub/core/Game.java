package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.config.GameConfig;
import com.dmcs.blaszkub.core.abstraction.IGame;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Coordinate;
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

    public boolean move(Coordinate coordinate) {
        if (!GameLogic.isMoveValid(coordinate, board)) {
            return false;
        }
        //setField
        return true;
    }

    public void start() {
        //wyciagnij z gameConfig rozmiar planszy -> initBoard
        //wyciagnij liczbe statkow i je rozmiesc, jak automat to losuj
        //jak manual podajac kordy statku ustawiaj go

        BoardPrinter.print(board);
    }

    private void initBoard() {
        this.board = new Board(gameConfig.getXAxisLength(), gameConfig.getYAxisLength());
    }
}
