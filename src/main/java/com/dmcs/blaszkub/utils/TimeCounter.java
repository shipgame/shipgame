package com.dmcs.blaszkub.utils;

import java.time.Instant;

public class TimeCounter {
    private Long startMilis;
    private Long endMilis;

    private Instant start;
    private Instant end;

    public void start() {
        startMilis = System.currentTimeMillis();
        start = Instant.now();
    }

    public Long getTimeSpentInMilis() {
        endMilis = System.currentTimeMillis();
        end = Instant.now();

        return end.getEpochSecond() - start.getEpochSecond();
    }

    public void reset() {
        startMilis = 0L;
        endMilis = 0L;
    }
}
