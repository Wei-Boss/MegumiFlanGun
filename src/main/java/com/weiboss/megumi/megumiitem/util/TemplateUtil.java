package com.weiboss.megumi.megumiitem.util;

import com.weiboss.megumi.megumiitem.Main;
import com.weiboss.megumi.megumiitem.constructor.*;
import com.weiboss.megumi.megumiitem.file.Config;
import com.weiboss.megumi.megumiitem.tadokoro.Attribute;
import com.weiboss.megumi.megumiitem.constructor.Template;
import com.weiboss.megumi.megumiitem.tadokoro.Wear;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TemplateUtil {
    private static Main plugin = Main.getInstance();

    public static ItemStack getItem(Template template, int amount) {
        String id = template.getId();
        int data = template.getData();
        String name = template.getName();
        String suffix = template.getSuffix();
        String display = template.getDisplay();
        List<String> lore = new ArrayList<>();
        HashMap<String, RangeValue> attributes = template.getAttributes();
        HashMap<String, Boolean> feature = template.getFeature();
        List<WearRate> wears = template.getWears();

        Random random = new Random();
        display = display.replace("%name%", name);
        Wear wear = getWear(wears);
        if (wear != null) display = display.replace("%wear%", wear.getName());
        if (suffix != null) display = display.replace("%suffix%", suffix);
        Boolean stat = feature.get("StatTrak");
        if (stat != null && stat) display = display.replace("%stattrak%", Config.Lore.StatTrakTag);

        double wearValue = 1d;
        if (wear != null) wearValue = random.nextDouble() * (wear.getMax() - wear.getMin()) + wear.getMin();

        for (String s : template.getLore()) {
            if (s.equalsIgnoreCase("%levellimit%")) {
                RangeValue range = attributes.get("LevelLimit");
                if (range == null) continue;
                int level = rounding(reservedTwo(range));
                lore.add(Config.Lore.LevelLimit.replace("%s%", String.valueOf(level)));
                continue;
            }
            if (s.equalsIgnoreCase("%nottransferable%")) {
                Boolean off = feature.get("NotTransferable");
                if (off == null) continue;
                if (off) lore.add(Config.Lore.NotTransferable);
                continue;
            }
            if (s.equalsIgnoreCase("%soulbound%")) {
                Boolean off = feature.get("Soulbound");
                if (off == null) continue;
                if (off) lore.add(Config.SoulBound.Unbound);
                continue;
            }
            if (s.equalsIgnoreCase("%stattrak%")) {
                Boolean off = feature.get("StatTrak");
                if (off == null) continue;
                if (off) lore.add(Config.Lore.StatTrak);
                continue;
            }
            if (s.equalsIgnoreCase("%wearvalue%")) {
                if (wear == null) continue;
                lore.add(Config.Lore.WearValue.replace("%value%", String.valueOf(wearValue)));
                continue;
            }
            if (s.equalsIgnoreCase("%health%")) {
                RangeValue range = attributes.get("Health");
                if (range == null) continue;
                double health = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.Health, health, false));
                continue;
            }
            if (s.equalsIgnoreCase("%damage%")) {
                RangeValue range = attributes.get("Damage");
                if (range == null) continue;
                double damage = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.Damage, damage, false));
                continue;
            }
            if (s.equalsIgnoreCase("%defense%")) {
                RangeValue range = attributes.get("Defense");
                if (range == null) continue;
                double defense = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.Defense, defense, false));
                continue;
            }
            if (s.equalsIgnoreCase("%ignoredamage%")) {
                RangeValue range = attributes.get("IgnoreDamage");
                if (range == null) continue;
                double ignoreDamage = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.IgnoreDamage, ignoreDamage, false));
                continue;
            }
            if (s.equalsIgnoreCase("%ignoredefense%")) {
                RangeValue range = attributes.get("IgnoreDefense");
                if (range == null) continue;
                double ignoreDefense = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.IgnoreDefense, ignoreDefense, false));
                continue;
            }
            if (s.equalsIgnoreCase("%vampire%")) {
                RangeValue range = attributes.get("Vampire");
                if (range == null) continue;
                double vampire = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.Vampire, vampire, true));
                continue;
            }
            if (s.equalsIgnoreCase("%hitchance%")) {
                RangeValue range = attributes.get("HitChance");
                if (range == null) continue;
                double hitChance = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.HitChance, hitChance, true));
                continue;
            }
            if (s.equalsIgnoreCase("%dodgechance%")) {
                RangeValue range = attributes.get("DodgeChance");
                if (range == null) continue;
                double dodgeChance = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.DodgeChance, dodgeChance, true));
                continue;
            }
            if (s.equalsIgnoreCase("%wreckchance%")) {
                RangeValue range = attributes.get("WreckChance");
                if (range == null) continue;
                double wreckChance = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.WreckChance, wreckChance, true));
                continue;
            }
            if (s.equalsIgnoreCase("%blockchance%")) {
                RangeValue range = attributes.get("BlockChance");
                if (range == null) continue;
                double blockChance = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.BlockChance, blockChance, true));
                continue;
            }
            if (s.equalsIgnoreCase("%blockrate%")) {
                RangeValue range = attributes.get("BlockRate");
                if (range == null) continue;
                double blockRate = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.BlockRate, blockRate, true));
                continue;
            }
            if (s.equalsIgnoreCase("%critchance%")) {
                RangeValue range = attributes.get("CritChance");
                if (range == null) continue;
                double critChance = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.CritChance, critChance, true));
                continue;
            }
            if (s.equalsIgnoreCase("%critrate%")) {
                RangeValue range = attributes.get("CritRate");
                if (range == null) continue;
                double critRate = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.CritRate, critRate, true));
                continue;
            }
            if (s.equalsIgnoreCase("%antiknockchance%")) {
                RangeValue range = attributes.get("AntiknockChance");
                if (range == null) continue;
                double antiknockChance = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.AntiknockChance, antiknockChance, true));
                continue;
            }
            if (s.equalsIgnoreCase("%movementspeed%")) {
                RangeValue range = attributes.get("MovementSpeed");
                if (range == null) continue;
                double speed = reservedTwo(range);
                lore.add(FlanGunUtil.getAttrLore(Attribute.MovementSpeed, speed, true));
                continue;
            }
            lore.add(WeiUtil.onReplace(s));
        }

        return WeiUtil.createItem(id, data, amount, display, lore, null);
    }

    private static double reservedTwo(RangeValue range) {
        double value = range.getMin();
        if (range.getMax() != null) value = getRandomRange(range.getMin(), range.getMax());
        DecimalFormat format = new DecimalFormat("#.00");
        return Double.valueOf(format.format(value));
    }

    private static int rounding(double value) {
        DecimalFormat format = new DecimalFormat("#");
        return Integer.parseInt(format.format(value));
    }

    private static double getRandomRange(double min, double max) {
        Random random = new Random();
        return random.nextDouble() * (max - min) + min;
    }

    private static Wear getWear(List<WearRate> wears) {
        String lottery = LotteryUtil.start(wears);
        if (lottery == null) return null;
        return plugin.getMyPluginManager().getWearMap().get(lottery);
    }
}
