package com.weiboss.megumi.megumiitem.constructor;

public class RangeValue {
    private Double min;
    private Double max;

    public RangeValue(Double min, Double max) {
        this.min = min;
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }
}