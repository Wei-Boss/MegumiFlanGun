package com.weiboss.megumi.megumiflangun.util;

import com.weiboss.megumi.megumiflangun.Main;
import com.weiboss.megumi.megumiflangun.flan.data.FlanGun;
import com.weiboss.megumi.megumiflangun.tadokoro.Attribute;
import com.weiboss.megumi.megumiflangun.file.Config;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class FlanGunUtil {
    private static Main plugin = Main.getInstance();

    public static Attribute getAttrType(String s) {
        for (Attribute attribute : Attribute.values()) {
            String lore = attribute.getLore();
            if (s.equalsIgnoreCase(lore)) {
                return attribute;
            }
        }
        return null;
    }

    public static int getLevelLimit(String lore) {
        String[] strings = Config.Lore.LevelLimit.split("%s%");
        for (String s : strings) {
            lore = lore.replace(s, "");
        }
        return Integer.parseInt(lore);
    }

    public static String getSoulBound(String lore) {
        String[] strings = Config.SoulBound.Bound.split("%s%");
        for (String s : strings) {
            lore = lore.replace(s, "");
        }
        return lore;
    }

    public static double getWearValue(String lore) {
        String[] strings = Config.Lore.WearValue.split("%value%");
        for (String s : strings) {
            lore = lore.replace(s, "");
        }
        if (!WeiUtil.isFloat(lore)) return 0d;
        return Double.valueOf(lore);
    }

    public static ItemStack bindPlayer(Player p, ItemStack item) {
        ItemStack bindItem = new ItemStack(item);
        ItemMeta meta = bindItem.getItemMeta();
        List<String> lore = meta.getLore();
        int index = lore.indexOf(Config.SoulBound.Unbound);
        lore.set(index, Config.SoulBound.Bound.replace("%s%", p.getName()));
        meta.setLore(lore);
        bindItem.setItemMeta(meta);
        return bindItem;
    }

    public static boolean isBindUser(ItemStack item, Player p) {
        FlanGun flanGun = new FlanGun(item);
        if (flanGun.getBind()) return false;
        if (flanGun.getBindUser() == null) return false;
        if (!flanGun.getBindUser().equalsIgnoreCase(p.getName())) return false;
        return true;
    }

    public static String getAttrLore(Attribute attribute, double value, boolean rate) {
        String lore = attribute.getLore();
        StringBuilder builder = new StringBuilder();
        builder
                .append(Config.Attr.Prefix)
                .append(lore)
                .append(Config.Attr.Part);
        if (attribute != Attribute.CritRate) {
            if (value >= 0)
                builder
                        .append(Config.Attr.Positive)
                        .append(value);
            else
                builder
                        .append(Config.Attr.Negative)
                        .append(value);
            if (rate)
                builder
                        .append(Config.Attr.Rate);
        }
        else {
            builder
                    .append(Config.Attr.Multiply)
                    .append(value);
        }
        return builder.toString();
    }

    public static boolean hasAttribute(String s) {
        for (Attribute attribute : Attribute.values()) {
            if (attribute.getType().equalsIgnoreCase(s)) return true;
        }
        return false;
    }
}
