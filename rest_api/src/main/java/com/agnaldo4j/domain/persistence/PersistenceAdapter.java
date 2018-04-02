package com.agnaldo4j.domain.persistence;

import com.agnaldo4j.domain.entities.Statistic;
import com.agnaldo4j.domain.entities.Transaction;

import java.io.IOException;
import java.time.ZonedDateTime;

public interface PersistenceAdapter {
    public ZonedDateTime now();

    public long nowMinusSeconds(long seconds);

    public void save(Transaction transaction) throws IOException;

    public Statistic statistic() throws IOException;
}
