package com.weiboss.megumi.megumiitem.constructor;

import lombok.Data;

@Data
public class WearRate {
    private String wear;
    private Double rate;

    public WearRate(String wear, Double rate) {
        this.wear = wear;
        this.rate = rate;
    }
}
