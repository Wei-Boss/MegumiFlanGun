package com.weiboss.megumi.megumiflangun.util;

import com.weiboss.megumi.megumiflangun.constructor.WearRate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LotteryUtil {

    public static String start(List<WearRate> map) {
        if (map == null || map.size() == 0) return null;
        List<Double> rate = new ArrayList<>();
        List<Double> totalRate = new ArrayList<>();
        double total = 0d;
        double i = 0d;
        for (WearRate wearRate : map) {
            rate.add(wearRate.getRate());
            total += wearRate.getRate();
        }
        if (total == 0d) return null;
        for (Double d : rate) {
            i += d;
            totalRate.add(i / total);
        }
        double random = Math.random();
        totalRate.add(random);
        Collections.sort(totalRate);
        int size = totalRate.indexOf(random);
        return map.get(size).getWear();
    }
}
