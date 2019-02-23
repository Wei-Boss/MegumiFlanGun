package com.weiboss.megumi.megumiitem.core.tadokoro;

import lombok.Data;

@Data
public class Wear {
    private String name;
    private Double min;
    private Double max;

    public Wear(String name, Double min, Double max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }
}
