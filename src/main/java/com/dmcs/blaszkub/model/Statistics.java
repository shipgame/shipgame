package com.dmcs.blaszkub.model;

import lombok.Data;

@Data
public class Statistics {
    private int allShoots;
    private int missedShoots;

    int getHitShoots() {
        return allShoots - missedShoots;
    }

    double getHitAccuracy() {
        return ((double) getHitShoots() / getAllShoots()) * 100;
    }

    public void increaseAllShoots() {
        allShoots++;
    }

    public void increaseMissedShoots() {
        missedShoots++;
    }

    public void print() {
        System.out.println("----- YOUR STATISTICS -----");
        System.out.println("Total shoots: " + allShoots);
        System.out.println("Hit shoots: " + getHitShoots());
        System.out.println("Accuracy: " + getHitAccuracy() + "%");
    }
}
