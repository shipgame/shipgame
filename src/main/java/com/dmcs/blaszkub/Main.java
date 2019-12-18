package com.dmcs.blaszkub;

import com.dmcs.blaszkub.config.CustomSettings;
import com.dmcs.blaszkub.config.GameConfig;
import com.dmcs.blaszkub.core.Game;
import com.dmcs.blaszkub.core.Player;
import com.dmcs.blaszkub.enums.ModeType;
import com.dmcs.blaszkub.model.Statistics;

public class Main {

    private static final int SIZE = 25;

    public static void main(String[] args) {
        Game game;
        CustomSettings customSettings = new CustomSettings();

        customSettings.setCustomGameConfig();
        if (customSettings.isSetByPlayer()) {
            game = new Game(customSettings.getGameConfig(), new Player(new Statistics()));
        } else {
            GameConfig gameConfig = new GameConfig(ModeType.AUTO, SIZE, SIZE);
            game = new Game(gameConfig, new Player(new Statistics()));
        }

        game.start();
    }
}
