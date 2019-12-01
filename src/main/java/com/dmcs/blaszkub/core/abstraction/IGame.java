package com.dmcs.blaszkub.core.abstraction;

import com.dmcs.blaszkub.model.Coordinate;

public interface IGame {
    boolean move(Coordinate coordinate);

    void start();
}
