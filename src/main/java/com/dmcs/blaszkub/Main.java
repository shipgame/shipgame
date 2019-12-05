package com.dmcs.blaszkub;

import com.dmcs.blaszkub.config.GameConfig;
import com.dmcs.blaszkub.core.Game;
import com.dmcs.blaszkub.core.Player;
import com.dmcs.blaszkub.enums.ModeType;

public class Main {

    public static void main(String[] args) {
        GameConfig gameConfig = new GameConfig(ModeType.AUTO, 4, 4);

        Game game = new Game(gameConfig, new Player());

        game.start();
    }
}
