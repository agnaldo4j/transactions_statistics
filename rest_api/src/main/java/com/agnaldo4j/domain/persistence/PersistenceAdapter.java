package com.agnaldo4j.domain.persistence;

import com.agnaldo4j.domain.entities.Statistic;
import com.agnaldo4j.domain.entities.Transaction;

import java.io.IOException;

public interface PersistenceAdapter {
    public void save(Transaction transaction) throws IOException;

    public Statistic statistic() throws IOException;
}
