package com.weiboss.megumi.megumiitem.tadokoro;

public class Wear {
    private String name;
    private Double min;
    private Double max;

    public Wear(String name, Double min, Double max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
