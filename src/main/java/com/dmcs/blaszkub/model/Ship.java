package com.dmcs.blaszkub.model;

import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.enums.ShipType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class Ship {
    private ShipType shipType;
    private List<Field> fields = new ArrayList<>();
    private boolean submerged;

    public Ship(ShipType shipType, List<Field> fields) {
        this.shipType = shipType;
        this.fields = fields;
    }

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
