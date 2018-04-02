package com.agnaldo4j.persistence.adapter;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class MockClockSystem implements ClockSystem {
    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(1522578899094L), ZoneOffset.UTC);
    }

    @Override
    public long nowMinusSeconds(long seconds) {
        return now().minusSeconds(seconds).toInstant().toEpochMilli();
    }
}
