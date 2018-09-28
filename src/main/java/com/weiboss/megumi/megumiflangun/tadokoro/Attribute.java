package com.weiboss.megumi.megumiflangun.tadokoro;


import com.weiboss.megumi.megumiflangun.Main;

import java.util.ArrayList;
import java.util.List;

public enum  Attribute {
    Health("Health", Main.getInstance().getFileManager().getAttrName("Health"), false),
    Damage("Damage", Main.getInstance().getFileManager().getAttrName("Damage"), false),
    Defense("Defense", Main.getInstance().getFileManager().getAttrName("Defense"), false),
    IgnoreDamage("IgnoreDamage", Main.getInstance().getFileManager().getAttrName("IgnoreDamage"), false),
    IgnoreDefense("IgnoreDefense", Main.getInstance().getFileManager().getAttrName("IgnoreDefense"), false),
    Vampire("Vampire", Main.getInstance().getFileManager().getAttrName("Vampire"), true),
    HitChance("HitChance", Main.getInstance().getFileManager().getAttrName("HitChance"), true),
    DodgeChance("DodgeChance", Main.getInstance().getFileManager().getAttrName("DodgeChance"), true),
    WreckChance("WreckChance", Main.getInstance().getFileManager().getAttrName("WreckChance"), true),
    BlockChance("BlockChance", Main.getInstance().getFileManager().getAttrName("BlockChance"), true),
    BlockRate("BlockRate", Main.getInstance().getFileManager().getAttrName("BlockRate"), true),
    CritChance("CritChance", Main.getInstance().getFileManager().getAttrName("CritChance"), true),
    CritRate("CritRate", Main.getInstance().getFileManager().getAttrName("CritRate"), true),
    AntiknockChance("AntiknockChance", Main.getInstance().getFileManager().getAttrName("AntiknockChance"), true),
    MovementSpeed("MovementSpeed", Main.getInstance().getFileManager().getAttrName("MovementSpeed"), true);

    private String type;
    private String lore;
    private boolean rate;

    private Attribute(String type, String lore, boolean rate) {
        this.type = type;
        this.lore = lore;
        this.rate = rate;
    }

    public static List<String> getAttribute() {
        List<String> attr = new ArrayList<>();
        for (Attribute attribute : values()) {
            attr.add(attribute.getType());
        }
        return attr;
    }

    public String getType() {
        return type;
    }

    public String getLore() {
        return lore;
    }

    public boolean isRate() {
        return rate;
    }
}
