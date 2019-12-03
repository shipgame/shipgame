package com.dmcs.blaszkub.data;

import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.model.Coordinate;
import com.dmcs.blaszkub.model.Field;

public class FieldData {
    public static Field getField(Coordinate coordinate) {
        return new Field(coordinate, FieldType.EMPTY);
    }
}
