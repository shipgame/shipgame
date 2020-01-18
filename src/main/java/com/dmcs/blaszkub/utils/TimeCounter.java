package com.dmcs.blaszkub.utils;

import java.time.Instant;

public class TimeCounter {

    private Instant start;
    private Instant end;

    public void start() {
        start = Instant.now();
    }

    public Long getTimeSpentInMilis() {
        end = Instant.now();

        return end.getEpochSecond() - start.getEpochSecond();
    }
}
