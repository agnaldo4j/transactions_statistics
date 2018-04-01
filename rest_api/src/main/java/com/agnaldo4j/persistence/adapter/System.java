package com.agnaldo4j.persistence.adapter;

import com.agnaldo4j.domain.entities.Statistic;
import com.agnaldo4j.domain.entities.Transaction;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class System implements Serializable {
    private final Set<Transaction> transactions;
    private Statistic statistic;

    public System() {
        this.transactions = new LinkedHashSet<>();
        this.statistic = new Statistic();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public Transaction[] transactions() {
        return this.transactions.toArray(new Transaction[0]);
    }

    public void updateStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public Statistic statistic() {
        return this.statistic;
    }
}
