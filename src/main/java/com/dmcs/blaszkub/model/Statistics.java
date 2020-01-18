package com.dmcs.blaszkub.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Statistics {
    private int allShoots;
    private int missedShoots;

    private Statistics() {

    }

    int getHitShoots() {
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
