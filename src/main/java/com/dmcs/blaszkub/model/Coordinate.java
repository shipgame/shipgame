package com.dmcs.blaszkub.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Scanner;

@AllArgsConstructor
@Data
public class Coordinate {

    private static final String ENTER_X_COORDINATE = "Enter X coordinate";
    private static final String ENTER_Y_COORDINATE = "Enter Y coordinate";

    private int x;
    private int y;

    public static Coordinate getCoordinatesFromPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ENTER_X_COORDINATE);
        int coordinateX = scanner.nextInt();

        System.out.println(ENTER_Y_COORDINATE);
        int coordinateY = scanner.nextInt();

        scanner.close();

        return new Coordinate(coordinateY, coordinateX);
    }
}
