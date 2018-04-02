package com.agnaldo4j.persistence.adapter.command;

import com.agnaldo4j.domain.entities.Transaction;
import com.agnaldo4j.persistence.Command;
import com.agnaldo4j.persistence.adapter.System;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class CleanOldTransactions implements Command<System> {

    private final Long initialTime;

    public CleanOldTransactions(long initialTime) {
        this.initialTime = initialTime;
    }

    @Override
    public void execute(System system) {
        Set<Transaction> newTransactions = new LinkedHashSet<>();

        Iterator<Transaction> transactions = system.transactions();
        while (transactions.hasNext()) {
            Transaction transaction = transactions.next();
            if (transaction.timestamp() >= this.initialTime)
                newTransactions.add(transaction);
        }

        if (newTransactions.size() > 0)
            system.updateTransactions(newTransactions);
    }
}
