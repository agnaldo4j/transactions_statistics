package com.agnaldo4j.persistence.adapter.query;

import com.agnaldo4j.domain.entities.Statistic;
import com.agnaldo4j.persistence.Query;
import com.agnaldo4j.persistence.adapter.System;

public class RetrieveStatistic implements Query<System, Statistic> {

    @Override
    public Statistic execute(System system) {
        return system.statistic().copy();
    }
}
