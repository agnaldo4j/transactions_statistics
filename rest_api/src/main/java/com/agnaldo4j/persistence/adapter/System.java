package com.agnaldo4j.persistence.adapter;

import com.agnaldo4j.domain.entities.Statistic;
import com.agnaldo4j.domain.entities.Transaction;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class System implements Serializable {
    private Set<Transaction> transactions;
    private Statistic statistic;

    public System() {
        this.transactions = new LinkedHashSet<>();
        this.statistic = new Statistic();
    }

    public int transactionsCount() {
        return this.transactions.size();
    }

    public void updateTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public Iterator<Transaction> transactions() {
        return this.transactions.iterator();
    }

    public void updateStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public Statistic statistic() {
        return this.statistic;
    }
}
