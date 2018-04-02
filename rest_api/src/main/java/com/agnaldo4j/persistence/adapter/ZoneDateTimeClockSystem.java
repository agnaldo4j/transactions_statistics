package com.agnaldo4j.persistence.adapter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ZoneDateTimeClockSystem implements ClockSystem {
    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.now(ZoneOffset.UTC);
    }

    @Override
    public long nowMinusSeconds(long seconds) {
        return now().minusSeconds(seconds).toInstant().toEpochMilli();

    }
}
