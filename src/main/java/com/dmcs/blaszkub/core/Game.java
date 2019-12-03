package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.config.GameConfig;
import com.dmcs.blaszkub.core.abstraction.IGame;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Ship;
import com.dmcs.blaszkub.model.Statistics;
import com.dmcs.blaszkub.utils.BoardPrinter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game implements IGame {

    private final GameConfig gameConfig;
    private boolean isFinished;
    private Board board;
    private Statistics statistics;
    private final Player player;

    public Game(GameConfig gameConfig, Player player) {
        this.gameConfig = gameConfig;
        this.player = player;
        initBoard();
    }

    public void start() {
        //TODO wyciagnij z gameConfig rozmiar planszy -> initBoard
        //wyciagnij liczbe statkow i je rozmiesc, jak automat to losuj
        //jak manual podajac kordy statku ustawiaj go

        //TODO tutaj pobieraj dane ze strumienia, w petli while poki gra nie jest skonczona

        //TODO na player wywolaj metode makeMove
        //TODO po kazdym ruchu sprawdzaj czy gra nie jest skonczona

        BoardPrinter.print(board);
    }

    private void initBoard() {
        this.board = new Board(gameConfig.getXAxisLength(), gameConfig.getYAxisLength());
    }

    private boolean isGameFinished() {
        return getBoard().getShips().stream().allMatch(Ship::isSubmerged);
    }
}
