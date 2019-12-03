package com.dmcs.blaszkub.data;

import com.dmcs.blaszkub.model.Field;
import com.dmcs.blaszkub.model.Ship;

import java.util.List;

public class ShipData {
    public static Ship getShip(List<Field> fields) {
        Ship ship = new Ship();
        ship.setFields(fields);
        return ship;
    }
}
