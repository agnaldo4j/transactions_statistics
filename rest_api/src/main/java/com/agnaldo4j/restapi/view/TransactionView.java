package com.agnaldo4j.restapi.view;

import com.agnaldo4j.domain.entities.Transaction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class TransactionView {

    private final long timestamp;

    private final double amount;

    @JsonCreator
    public TransactionView(
        @JsonProperty("amount") @NotNull double amount,
        @JsonProperty("timestamp") long timestamp
    ) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Transaction toDomain() {
        return new Transaction(amount, timestamp);
    }
}
