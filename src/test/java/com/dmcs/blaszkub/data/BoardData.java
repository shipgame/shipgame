package com.dmcs.blaszkub.data;

import com.dmcs.blaszkub.model.Board;

public class BoardData {
    public static Board getBoard(int xAxisLength, int yAxisLength) {
        return new Board(xAxisLength, yAxisLength);
    }
}
