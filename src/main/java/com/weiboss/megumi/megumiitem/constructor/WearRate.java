package com.weiboss.megumi.megumiitem.constructor;

public class WearRate {
    private String wear;
    private Double rate;

    public WearRate(String wear, Double rate) {
        this.wear = wear;
        this.rate = rate;
    }

    public String getWear() {
        return wear;
    }

    public Double getRate() {
        return rate;
    }
}
