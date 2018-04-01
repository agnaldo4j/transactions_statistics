package com.agnaldo4j.persistence.adapter.command;

import com.agnaldo4j.domain.entities.Transaction;
import com.agnaldo4j.persistence.Command;
import com.agnaldo4j.persistence.adapter.System;

public class SaveTransaction implements Command<System> {

    private final Transaction transaction;

    public SaveTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void execute(System system) {
        system.addTransaction(this.transaction);
    }
}
