package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.core.abstraction.IPlayer;
import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.model.Board;
import com.dmcs.blaszkub.model.Field;
import com.dmcs.blaszkub.model.Ship;
import com.dmcs.blaszkub.model.Statistics;

public class Player implements IPlayer {

    private static final String MISS = "MISS";
    private static final String HIT = "HIT";

    private final Statistics statistics;

    public Player(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public void makeMove(int x, int y, Board board) {
        Field field = board.getFieldByCoordinates(x, y);
        if (field.isEmpty()) {
            updateStatisticsAfterMissedShoot();
            board.setFieldByCoordinate(field.getCoordinate(), FieldType.SHOOTED);
        } else if (field.isOccupiedByShip()) {
            updateStatisticsAfterHitShoot();
            board.setFieldByCoordinate(field.getCoordinate(), FieldType.SHOOTED_SHIP);
            Ship ship = board.getShipByCoordinate(field.getCoordinate());

            setSubmergedShip(board, ship);
        }
    }

    public void printPlayerStatistics() {
        statistics.print();
    }

    private void updateStatisticsAfterMissedShoot() {
        System.out.println(MISS);
        statistics.increaseAllShoots();
        statistics.increaseMissedShoots();
    }

    private void updateStatisticsAfterHitShoot() {
        System.out.println(HIT);
        statistics.increaseAllShoots();
    }

    private void setSubmergedShip(Board board, Ship ship) {
        if (ship.areAllFieldsShipShooted()) {
            ship.setShipAsSubmerged();
            board.fillFieldsAroundSubmergedShip(ship);
        }
    }
}
