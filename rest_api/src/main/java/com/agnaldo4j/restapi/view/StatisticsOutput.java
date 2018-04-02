package com.agnaldo4j.restapi.view;

import com.agnaldo4j.restapi.json.APIViews;
import com.agnaldo4j.domain.entities.Statistic;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        this.avg = round(statistic.avg());
        this.min = round(statistic.min());
        this.max = round(statistic.max());
        this.sum = round(statistic.sum());
    }

    private double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
