package com.dmcs.blaszkub.model;

import com.dmcs.blaszkub.enums.ShipType;
import lombok.Data;

import java.util.List;

@Data
public class Ship {
    private ShipType shipType;
    private List<Coordinate> coordinates;
}
