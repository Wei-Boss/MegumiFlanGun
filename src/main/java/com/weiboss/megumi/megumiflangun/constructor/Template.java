package com.weiboss.megumi.megumiflangun.constructor;

import java.util.HashMap;
import java.util.List;

public class Template {
    private String name;
    private String suffix;
    private RangeValue levelLimit;
    private Integer id;
    private Integer data;
    private String display;
    private List<String> lore;
    private HashMap<String, RangeValue> attributes;
    private HashMap<String, Boolean> feature;
    private List<WearRate> wears;

    public Template(String name, String suffix, RangeValue levelLimit, Integer id, Integer data, String display, List<String> lore, HashMap<String, RangeValue> attributes, HashMap<String, Boolean> feature, List<WearRate> wears) {
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

    public String getName() {
        return name;
    }

    public String getSuffix() {
        return suffix;
    }

    public RangeValue getLevelLimit() {
        return levelLimit;
    }

    public Integer getId() {
        return id;
    }

    public Integer getData() {
        return data;
    }

    public String getDisplay() {
        return display;
    }

    public List<String> getLore() {
        return lore;
    }

    public HashMap<String, RangeValue> getAttributes() {
        return attributes;
    }

    public HashMap<String, Boolean> getFeature() {
        return feature;
    }

    public List<WearRate> getWears() {
        return wears;
    }
}
