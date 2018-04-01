package com.agnaldo4j.domain.entities;

import java.io.Serializable;

public class Transaction implements Serializable {

    private final double amount;
    private final long timestamp;

    public Transaction(double amount, long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public double amount() {
        return this.amount;
    }

    public long timestamp() {
        return this.timestamp;
    }
}
