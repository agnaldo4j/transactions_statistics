package com.agnaldo4j.domain.entities;

import java.io.Serializable;

public class Statistic implements Serializable {

    private long count = 0l;
    private double minAmount;
    private double maxAmount;
    private double sum = 0d;

    public Statistic() {
        this(0, 0, 0, 0);
    }

    public Statistic(long count, double min, double max, double sum) {
        this.count = count;
        this.minAmount = min;
        this.maxAmount = max;
        this.sum = sum;
    }

    public void calculate(double amount) {
        this.sum += amount;
        if (this.count == 0 || this.minAmount > amount) this.minAmount = amount;
        if (this.count == 0 || this.maxAmount < amount) this.maxAmount = amount;
        this.count++;
    }

    public Statistic copy() {
        return new Statistic(count, minAmount, maxAmount, sum);
    }

    public long count() {
        return this.count;
    }

    public double min() {
        return this.minAmount;
    }

    public double max() {
        return this.maxAmount;
    }

    public double sum() {
        return this.sum;
    }

    public double avg() {
        if (count == 0) return this.sum;
        else return this.sum / this.count;
    }
}
