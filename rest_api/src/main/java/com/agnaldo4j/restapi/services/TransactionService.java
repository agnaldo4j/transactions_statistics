package com.agnaldo4j.restapi.services;

import com.agnaldo4j.domain.entities.Transaction;
import com.agnaldo4j.domain.persistence.PersistenceAdapter;
import com.agnaldo4j.restapi.exceptions.OldTransaction;
import com.agnaldo4j.restapi.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class TransactionService {

    public static final int TRANSACTION_TIME_WINDOW_SECONDS = 60;
    @Autowired
    private PersistenceAdapter persistenceAdapter;

    public void save(Transaction transaction) {
        try {
            ZonedDateTime now = ZonedDateTime.now( ZoneOffset.UTC );
            long time = now.minusSeconds(TRANSACTION_TIME_WINDOW_SECONDS).toInstant().toEpochMilli();
            persistenceAdapter.save(transaction);
            if (transaction.timestamp() < time) throw new OldTransaction("The transaction was generated more than 60 seconds ago");
        } catch (IOException e) {
            throw new PersistenceException("Error when try persit transaction");
        }
    }
}
