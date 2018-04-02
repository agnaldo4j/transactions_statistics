package com.agnaldo4j.restapi.services;

import com.agnaldo4j.domain.entities.Transaction;
import com.agnaldo4j.domain.persistence.PersistenceAdapter;
import com.agnaldo4j.restapi.exceptions.OldTransactionException;
import com.agnaldo4j.restapi.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TransactionService {

    public static final int TRANSACTION_TIME_WINDOW_SECONDS = 60;
    @Autowired
    private PersistenceAdapter persistenceAdapter;

    public void save(Transaction transaction) {
        try {
            long time = persistenceAdapter.nowMinusSeconds(TRANSACTION_TIME_WINDOW_SECONDS);
            persistenceAdapter.save(transaction);
            if (transaction.timestamp() < time) throw new OldTransactionException("The transaction was generated more than 60 seconds ago");
        } catch (IOException e) {
            throw new PersistenceException("Error when try save transaction");
        }
    }
}
