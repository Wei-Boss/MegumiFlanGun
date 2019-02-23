package com.weiboss.megumi.megumiitem.constructor;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class Template {
    private String name;
    private String suffix;
    private RangeValue levelLimit;
    private String id;
    private Integer data;
    private String display;
    private List<String> lore;
    private HashMap<String, RangeValue> attributes;
    private HashMap<String, Boolean> feature;
    private List<WearRate> wears;

    public Template(String name, String suffix, RangeValue levelLimit, String id, Integer data, String display, List<String> lore, HashMap<String, RangeValue> attributes, HashMap<String, Boolean> feature, List<WearRate> wears) {
        this.name = name;
        this.suffix = suffix;
        this.levelLimit = levelLimit;
        this.id = id;
        this.data = data;
        this.display = display;
        this.lore = lore;
        this.attributes = attributes;
        this.feature = feature;
        this.wears = wears;
    }
}
