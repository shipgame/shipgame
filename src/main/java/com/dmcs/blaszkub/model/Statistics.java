package com.dmcs.blaszkub.model;

import lombok.Data;

@Data
public class Statistics {
    private int allShoots;
    private int missedShoots;

    double getHitShoots() {
        return allShoots - missedShoots;
    }

    double getHitAccuracy() {
        return (getHitShoots() / getAllShoots()) * 100;
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
