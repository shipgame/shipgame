package com.dmcs.blaszkub;

import com.dmcs.blaszkub.core.AutomaticShipPlacer;
import com.dmcs.blaszkub.data.BoardData;
import com.dmcs.blaszkub.enums.ShipType;
import com.dmcs.blaszkub.exception.AutomaticPlacingShipsException;
import com.dmcs.blaszkub.model.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class AutomaticShipPlacerTest {

    @Test
    public void should_place_three_one_field_ships() {
        Board board = BoardData.getBoard(8, 8);

        List<ShipType> shipConfigs = Arrays.asList(
                ShipType.ONE,
                ShipType.ONE,
                ShipType.ONE
        );

        AutomaticShipPlacer automaticShipPlacer = new AutomaticShipPlacer();
        automaticShipPlacer.placeShips(shipConfigs, board);

        assertSame(board.getShips().size(), 3);

    }

    /*
    Ships:
     ONE, ONE
     TWO
     THREE
     FOUR
     */
    @Test
    public void should_place_ships_scenario_1() {
        Board board = BoardData.getBoard(20, 20);

        List<ShipType> shipConfigs = Arrays.asList(
                ShipType.ONE,
                ShipType.ONE,
                ShipType.TWO,
                ShipType.THREE,
                ShipType.FOUR
        );

        AutomaticShipPlacer automaticShipPlacer = new AutomaticShipPlacer();
        automaticShipPlacer.placeShips(shipConfigs, board);

        assertSame(board.getShips().size(), 5);

    }

    @Test
    public void should_throw_automatic_placing_ship_exception_when_can_not_place_ships_automatically() {
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
