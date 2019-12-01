package com.dmcs.blaszkub.model;

import lombok.Data;

@Data
public class Statistics {
    private int allShoots;
    private int missedShoots;

    public int getHittedShoots() {
        return allShoots - missedShoots;
    }

    public int getHitAccuracy() {
        return (getHittedShoots() / getAllShoots()) * 100;
    }
}
