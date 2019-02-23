package com.weiboss.megumi.megumiitem.constructor;

import lombok.Data;

@Data
public class RangeValue {
    private Double min;
    private Double max;

    public RangeValue(Double min, Double max) {
        this.min = min;
        this.max = max;
    }
}