package com.dmcs.blaszkub.core.abstraction;

import com.dmcs.blaszkub.model.Board;

public interface IPlayer {
    void makeMove(int x, int y, Board board);
}
