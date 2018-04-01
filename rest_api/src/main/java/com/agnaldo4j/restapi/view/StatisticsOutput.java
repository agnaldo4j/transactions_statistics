package com.agnaldo4j.restapi.view;

import com.agnaldo4j.restapi.json.APIViews;
import com.agnaldo4j.domain.entities.Statistic;
import com.fasterxml.jackson.annotation.JsonView;

public class StatisticsOutput {
    @JsonView(APIViews.Public.class)
    private long count;

    @JsonView(APIViews.Public.class)
    private double min;

    @JsonView(APIViews.Public.class)
    private double max;

    @JsonView(APIViews.Public.class)
    private double sum;

    @JsonView(APIViews.Public.class)
    private double avg;

    public StatisticsOutput(Statistic statistic) {
        this.count = statistic.count();
        this.avg = statistic.avg();
        this.min = statistic.min();
        this.max = statistic.max();
        this.sum = statistic.sum();
    }
}
