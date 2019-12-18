package com.dmcs.blaszkub.core;

import com.dmcs.blaszkub.enums.FieldType;
import com.dmcs.blaszkub.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlayerTest {

    private Board board;
    private Statistics statisticsMock;

    private Player objectUnderTest;

    @BeforeEach
    void setUp() {
        board = new Board(10, 10);

        statisticsMock = mock(Statistics.class);
        objectUnderTest = new Player(statisticsMock);
    }

    @Test
    void should_make_move_for_empty_field() {
        objectUnderTest.makeMove(5, 5, board);

        assertAll(
                () -> verify(statisticsMock).increaseMissedShoots(),
                () -> verify(statisticsMock).increaseAllShoots(),
                () -> assertEquals(FieldType.SHOOTED, board.getFieldByCoordinates(5, 5).getFieldType())
        );
    }
}