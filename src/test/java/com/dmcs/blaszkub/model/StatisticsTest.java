package com.dmcs.blaszkub.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    private Statistics statistics;

    @BeforeEach
    void setUp() {
        statistics = new Statistics();
    }

    @Test
    void should_increase_allShoots() {
        statistics.setAllShoots(9);
        statistics.increaseAllShoots();

        assertEquals(10, statistics.getAllShoots());
    }

    @Test
    void should_increase_missedShoots() {
        statistics.setMissedShoots(9);
        statistics.increaseMissedShoots();

        assertEquals(10, statistics.getMissedShoots());
    }

    @Test
    void should_calculate_hitShoots() {
        statistics.setAllShoots(10);
        statistics.setMissedShoots(5);

        assertEquals(5, statistics.getHitShoots());
    }

    @Test
    void should_calculate_hitAccuracy() {
        statistics.setAllShoots(10);
        statistics.setMissedShoots(5);

        assertEquals(50, statistics.getHitAccuracy());
    }
}