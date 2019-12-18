package com.dmcs.blaszkub.config;

import com.dmcs.blaszkub.enums.ModeType;
import com.dmcs.blaszkub.enums.ShipType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class GameConfig {

    private final List<ShipType> shipConfigs = Arrays.asList(
            ShipType.ONE,
            ShipType.TWO,
            ShipType.THREE,
            ShipType.FOUR
    );

    private ModeType modeType;
    private int xAxisLength;
    private int yAxisLength;

    public GameConfig(ModeType modeType, int xAxisLength, int yAxisLength) {
        this.modeType = modeType;
        this.xAxisLength = xAxisLength;
        this.yAxisLength = yAxisLength;
    }
}
