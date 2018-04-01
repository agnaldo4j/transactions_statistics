package com.agnaldo4j.persistence.adapter.command;

import com.agnaldo4j.domain.entities.Statistic;
import com.agnaldo4j.persistence.Command;
import com.agnaldo4j.persistence.adapter.System;

public class UpdateStatistic implements Command<System> {

    private final Statistic statistic;

    public UpdateStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    @Override
    public void execute(System system) {
        system.updateStatistic(statistic);
    }
}
