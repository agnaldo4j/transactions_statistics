package com.agnaldo4j.persistence.adapter.query;

import com.agnaldo4j.domain.entities.Statistic;
import com.agnaldo4j.domain.entities.Transaction;
import com.agnaldo4j.persistence.Query;
import com.agnaldo4j.persistence.adapter.System;

public class CalculateStatistics implements Query<System, Statistic> {

    private final Long initialTime;
    private final Statistic statistic;

    public CalculateStatistics(Long initialTime) {
        this.initialTime = initialTime;
        this.statistic = new Statistic();
    }

    @Override
    public Statistic execute(System system) {
        Transaction[] transactions = system.transactions();
        int totalElements = transactions.length - 1;
        for (int i = totalElements; i > -1; i--) {
            calculateWhenInTimeRange(transactions[i]);
        }
        return statistic;
    }

    private void calculateWhenInTimeRange(Transaction transaction) {
        if (transaction.timestamp() >= this.initialTime)
            this.statistic.calculate(transaction.amount());
    }
}
