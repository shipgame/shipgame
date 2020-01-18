package com.dmcs.blaszkub.utils;

import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Field;
import lombok.Data;

@Data
public class BoardPrinter {

    private BoardPrinter() {
    }

    public static void print(Board board) {
        for (int i = 0; i < board.getXAxisLength(); i++) {
            for (int j = 0; j < board.getYAxisLength(); j++) {
                Field field = board.getBoard()[i][j];
                System.out.print(field.getFieldType());
            }
            System.out.println("");
        }
    }
}
