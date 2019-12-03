package com.dmcs.blaszkub.model;

import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.enums.ShipType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Ship {
    private ShipType shipType;
    private List<Field> fields = new ArrayList<>();
    private boolean submerged;

    public boolean areAllFieldsShipShooted() {
        return fields.stream().allMatch(Field::isShootedShip);
    }

    public void setShipAsSubmerged() {
        fields.forEach(f -> f.setFieldType(FieldType.SUBMERGED_SHIP));
        submerged = true;
    }

    public void addField(Field field) {
        getFields().add(field);
    }
}
