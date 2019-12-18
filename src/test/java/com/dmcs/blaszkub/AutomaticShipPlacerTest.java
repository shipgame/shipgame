package com.dmcs.blaszkub;

import com.dmcs.blaszkub.config.Constants;
import com.dmcs.blaszkub.core.AutomaticShipPlacer;
import com.dmcs.blaszkub.data.BoardData;
import com.dmcs.blaszkub.enums.ShipType;
import com.dmcs.blaszkub.exception.AutomaticPlacingShipsException;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.utils.BoardPrinter;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertSame;

class AutomaticShipPlacerTest {

    @BeforeEach
    void setup() {
        Constants.MAX_AUTOMATIC_PLACING_SHIPS_TIME_IN_SECONDS = 30L;
    }

    @AfterEach
    private void printLine() {
        System.out.println("");
        System.out.println("-------------------------------------");
    }

    @Test
    void should_place_three_one_field_ships() {
        Board board = BoardData.getBoard(8, 8);

        List<ShipType> shipConfigs = Arrays.asList(
                ShipType.ONE,
                ShipType.ONE,
                ShipType.ONE
        );

        AutomaticShipPlacer automaticShipPlacer = new AutomaticShipPlacer();
        automaticShipPlacer.placeShips(shipConfigs, board);

        assertSame(board.getShips().size(), 3);
        BoardPrinter.print(board);
    }

    @Test
    void should_place_two_two_field_ships() {
        Board board = BoardData.getBoard(8, 8);

        List<ShipType> shipConfigs = Arrays.asList(
                ShipType.TWO,
                ShipType.TWO
        );

        AutomaticShipPlacer automaticShipPlacer = new AutomaticShipPlacer();
        automaticShipPlacer.placeShips(shipConfigs, board);

        assertSame(board.getShips().size(), 2);
        BoardPrinter.print(board);
    }

    /*
    Ships:
     ONE, ONE
     TWO
     THREE
     FOUR
     */

    @Test
    void should_place_ships_scenario_1() {
        Board board = BoardData.getBoard(15, 15);

        List<ShipType> shipConfigs = Arrays.asList(
                ShipType.ONE,
                ShipType.ONE,
                ShipType.TWO,
                ShipType.THREE
        );

        AutomaticShipPlacer automaticShipPlacer = new AutomaticShipPlacer();
        automaticShipPlacer.placeShips(shipConfigs, board);

        assertSame(board.getShips().size(), 4);
        BoardPrinter.print(board);
    }


    /*
   Ships:
    ONE, ONE, ONE, ONE
    TWO, TWO
    */
    @Test
    void should_place_ships_scenario_2() {
        Board board = BoardData.getBoard(12, 12);

        List<ShipType> shipConfigs = Arrays.asList(
                ShipType.ONE,
                ShipType.ONE,
                ShipType.ONE,
                ShipType.ONE,
                ShipType.TWO,
                ShipType.TWO
        );

        AutomaticShipPlacer automaticShipPlacer = new AutomaticShipPlacer();
        automaticShipPlacer.placeShips(shipConfigs, board);

        assertSame(board.getShips().size(), 6);
        BoardPrinter.print(board);
    }

    /*
 Ships:
  ONE, ONE
  FOUR
  */
    @Test
    void should_place_ships_scenario_3() {
        Board board = BoardData.getBoard(15, 15);

        List<ShipType> shipConfigs = Arrays.asList(
                ShipType.ONE,
                ShipType.ONE,
                ShipType.FOUR
        );

        AutomaticShipPlacer automaticShipPlacer = new AutomaticShipPlacer();
        automaticShipPlacer.placeShips(shipConfigs, board);

        assertSame(board.getShips().size(), 3);
        BoardPrinter.print(board);
    }

    @Test
    void should_throw_automatic_placing_ship_exception_when_can_not_place_ships_automatically() {
        Board board = BoardData.getBoard(4, 4);

        List<ShipType> shipConfigs = Arrays.asList(
                ShipType.FOUR,
                ShipType.FOUR,
                ShipType.FOUR
        );

        AutomaticShipPlacer automaticShipPlacer = new AutomaticShipPlacer();

        Assertions.assertThrows(AutomaticPlacingShipsException.class, () -> {
            automaticShipPlacer.placeShips(shipConfigs, board);
        });
    }
}
