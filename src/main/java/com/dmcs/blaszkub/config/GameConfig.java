package com.dmcs.blaszkub.config;

import com.dmcs.blaszkub.enums.ModeType;
import lombok.Data;

@Data
public class GameConfig {
    private ModeType modeType;
    private int shipNumber;

    private int xAxisLength;
    private int yAxisLength;

    public GameConfig(ModeType modeType, int shipNumber, int xAxisLength, int yAxisLength) {
        this.modeType = modeType;
        this.shipNumber = shipNumber;
        this.xAxisLength = xAxisLength;
        this.yAxisLength = yAxisLength;
    }
}
