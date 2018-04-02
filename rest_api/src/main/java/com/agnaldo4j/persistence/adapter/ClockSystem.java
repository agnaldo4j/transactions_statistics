package com.agnaldo4j.persistence.adapter;

import java.time.ZonedDateTime;

public interface ClockSystem {
    public ZonedDateTime now();

    public long nowMinusSeconds(long seconds);
}
