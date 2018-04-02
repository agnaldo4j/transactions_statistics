package com.agnaldo4j.persistence.adapter.query;

import com.agnaldo4j.persistence.Query;
import com.agnaldo4j.persistence.adapter.System;

public class CountTransactions implements Query<System, Integer> {

    @Override
    public Integer execute(System system) {
        return system.transactionsCount();
    }
}
