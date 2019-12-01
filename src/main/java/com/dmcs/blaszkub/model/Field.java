package com.dmcs.blaszkub.model;

import com.dmcs.blaszkub.enums.FieldType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Field {
    private Coordinate coordinate;
    private FieldType fieldType;

    public boolean isEmpty() {
        return getFieldType() != null && getFieldType().equals(FieldType.EMPTY);
    }

    public boolean isShooted() {
        return getFieldType() != null && getFieldType().equals(FieldType.SHOOTED);
    }

    public boolean isShootedShip() {
        return getFieldType() != null && getFieldType().equals(FieldType.SHOOTED_SHIP);
    }
}
