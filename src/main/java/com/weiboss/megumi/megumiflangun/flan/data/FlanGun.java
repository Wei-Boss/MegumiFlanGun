package com.weiboss.megumi.megumiflangun.flan.data;

import com.weiboss.megumi.megumiflangun.tadokoro.Attribute;
import com.weiboss.megumi.megumiflangun.file.Config;
import com.weiboss.megumi.megumiflangun.util.FlanGunUtil;
import com.weiboss.megumi.megumiflangun.util.RegularUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class FlanGun {
    private ItemStack item;
    private Boolean trade;
    private Boolean bind;
    private Integer statTrak;
    private Integer levelLimit;
    private Double wearValue;
    private String bindUser;
    private AttrData attrData;

    public FlanGun(Player p, ItemStack... items) {
        this.item = null;
        this.trade = null;
        this.bind = null;
        this.statTrak = null;
        this.levelLimit = null;
        this.wearValue = null;
        this.bindUser = null;
        this.attrData = new AttrData();

        forItem:
        for (ItemStack item : items) {
            if (item == null || item.getItemMeta() == null) continue;
            ItemMeta meta = item.getItemMeta();
            if (meta.getLore() == null || meta.getLore().size() == 0) continue;
            for (String s : meta.getLore()) {
                try {
                    if (s.equalsIgnoreCase(Config.SoulBound.Unbound)) {
                        if (p != null) continue forItem;
                    }
                    if (RegularUtil.isLevelLimit(s)) {
                        int i = FlanGunUtil.getLevelLimit(s);
                        if (p != null && p.getLevel() < i) continue forItem;
                    }
                    if (RegularUtil.isSoulBound(s)) {
                        if (p != null && !p.getName().equalsIgnoreCase(FlanGunUtil.getSoulBound(s))) continue forItem;
                    }
                    if (RegularUtil.isAttribute(s)) {
                        s = s
                                .replace(Config.Attr.Prefix, "")
                                .replace(Config.Attr.Positive, "")
                                .replace(Config.Attr.Negative, "")
                                .replace(Config.Attr.Rate, "");
                        String attribute = s.split(Config.Attr.Part)[0];
                        String value = s.split(Config.Attr.Part)[1];
                        Attribute attr = FlanGunUtil.getAttrType(attribute);
                        float f = Float.valueOf(value);
                        if (attr == null) continue;
                        switch (attr) {
                            case Health:
                                attrData.addHealth(f);
                                break;
                            case Damage:
                                attrData.addDamage(f);
                                break;
                            case Defense:
                                attrData.addDefense(f);
                                break;
                            case IgnoreDamage:
                                attrData.addIgnoreDamage(f);
                                break;
                            case IgnoreDefense:
                                attrData.addIgnoreDefense(f);
                                break;
                            case Vampire:
                                attrData.addVampire(f/100);
                                break;
                            case HitChance:
                                attrData.addHitChance(f / 100);
                                break;
                            case DodgeChance:
                                attrData.addDodgeChance(f / 100);
                                break;
                            case WreckChance:
                                attrData.addWreckChance(f / 100);
                                break;
                            case BlockChance:
                                attrData.addBlockChance(f / 100);
                                break;
                            case BlockRate:
                                attrData.addBlockRate(f / 100);
                                break;
                            case CritChance:
                                attrData.addCritChance(f / 100);
                                break;
                            case CritRate:
                                attrData.addCritRate(f / 100);
                                break;
                            case AntiknockChance:
                                attrData.addAntiKnockChance(f / 100);
                                break;
                            case MovementSpeed:
                                attrData.addMovementSpeed(f / 100);
                                break;
                        }
                    }
                }
                catch (Exception ignored) {

                }
            }
        }
    }

    public FlanGun(ItemStack item) {
        this.item = null;
        this.trade = false;
        this.bind = false;
        this.statTrak = null;
        this.levelLimit = null;
        this.wearValue = null;
        this.bindUser = null;
        this.attrData = new AttrData();

        if (item == null || item.getItemMeta() == null) return;
        this.item = new ItemStack(item);
        ItemMeta meta = item.getItemMeta();
        if (meta.getLore() == null || meta.getLore().size() == 0) return;
        for (String s : meta.getLore()) {
            try {
                if (!RegularUtil.isAttribute(s)) {
                    if (s.equalsIgnoreCase(Config.Lore.NotTransferable)) {
                        this.trade = true;
                        continue;
                    }
                    if (s.equalsIgnoreCase(Config.Lore.StatTrak)) {
                        this.statTrak = 0;
                        continue;
                    }
                    if (s.equalsIgnoreCase(Config.SoulBound.Unbound)) {
                        this.bind = true;
                        continue;
                    }
                    if (RegularUtil.isLevelLimit(s)) {
                        this.levelLimit = FlanGunUtil.getLevelLimit(s);
                        continue;
                    }
                    if (RegularUtil.isSoulBound(s)) {
                        this.bindUser = FlanGunUtil.getSoulBound(s);
                        continue;
                    }
                    if (RegularUtil.isWearValue(s)) {
                        this.wearValue = FlanGunUtil.getWearValue(s);
                        continue;
                    }
                    continue;
                }
                s = s
                        .replace(Config.Attr.Prefix, "")
                        .replace(Config.Attr.Positive, "")
                        .replace(Config.Attr.Negative, "")
                        .replace(Config.Attr.Rate, "");
                String attribute = s.split(Config.Attr.Part)[0];
                String value = s.split(Config.Attr.Part)[1];
                Attribute attr = FlanGunUtil.getAttrType(attribute);
                float f = Float.valueOf(value);
                if (attr == null) continue;
                switch (attr) {
                    case Health:
                        attrData.addHealth(f);
                        break;
                    case Damage:
                        attrData.addDamage(f);
                        break;
                    case Defense:
                        attrData.addDefense(f);
                        break;
                    case IgnoreDamage:
                        attrData.addIgnoreDamage(f);
                        break;
                    case IgnoreDefense:
                        attrData.addIgnoreDefense(f);
                        break;
                    case Vampire:
                        attrData.addVampire(f/100);
                        break;
                    case HitChance:
                        attrData.addHitChance(f/100);
                        break;
                    case DodgeChance:
                        attrData.addDodgeChance(f/100);
                        break;
                    case WreckChance:
                        attrData.addWreckChance(f/100);
                        break;
                    case BlockChance:
                        attrData.addBlockChance(f/100);
                        break;
                    case BlockRate:
                        attrData.addBlockRate(f/100);
                        break;
                    case CritChance:
                        attrData.addCritChance(f/100);
                        break;
                    case CritRate:
                        attrData.addCritRate(f/100);
                        break;
                    case AntiknockChance:
                        attrData.addAntiKnockChance(f/100);
                        break;
                    case MovementSpeed:
                        attrData.addMovementSpeed(f/100);
                        break;
                }
            }
            catch (Exception ignored) {
            }
        }
    }

    public ItemStack getItem() {
        return item;
    }

    public Boolean getTrade() {
        return trade;
    }

    public Boolean getBind() {
        return bind;
    }

    public Integer getStatTrak() {
        return statTrak;
    }

    public Integer getLevelLimit() {
        return levelLimit;
    }

    public Double getWearValue() {
        return wearValue;
    }

    public String getBindUser() {
        return bindUser;
    }

    public AttrData getAttrData() {
        return attrData;
    }

    public void setAttrData(AttrData attrData) {
        this.attrData = attrData;
    }
}
