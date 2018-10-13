package com.weiboss.megumi.megumiitem.file;

import com.weiboss.megumi.megumiitem.Main;
import com.weiboss.megumi.megumiitem.util.WeiUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Config {

    private static YamlConfiguration getConfig() {
        return Main.getInstance().getFileManager().getConfig();
    }

    private static String get(String path) {
        return WeiUtil.onReplace(getConfig().getString(path));
    }

    public final static String Prefix = get("Prefix");
    public final static String PreviewDisplay = get("Config.PreviewDisplay");
    public final static List<String> CmdBlackList = getConfig().getStringList("Config.TransferableBlackList");

    public static class Vampire {
        public final static Boolean Enable = getConfig().getBoolean("Config.Vampire.Enable");
        public final static Integer Time = getConfig().getInt("Config.Vampire.Time");
        public final static Integer Amount = getConfig().getInt("Config.Vampire.Amount");
        public final static Integer MaxValue = getConfig().getInt("Config.Vampire.MaxValue");
    }

    public static class Lore {
        public final static String NotTransferable = get("Config.Lore.NotTransferable");
        public final static String LevelLimit = get("Config.Lore.LevelLimit");
        public final static String StatTrak = get("Config.Lore.StatTrak");
        public final static String StatTrakStat = get("Config.Lore.StatTrakStat");
        public final static String StatTrakTag = get("Config.Lore.StatTrakTag");
        public final static String WearValue = get("Config.Lore.WearValue");
    }

    public static class Attr {
        public final static String Prefix = get("Config.Attribute.Prefix");
        public final static String Part = get("Config.Attribute.Part");
        public final static String Positive = get("Config.Attribute.Positive");
        public final static String Negative = get("Config.Attribute.Negative");
        public final static String Multiply = get("Config.Attribute.Multiply");
        public final static String Rate = get("Config.Attribute.Rate");
    }

    public static class SoulBound {
        public final static String Unbound = get("SoulBound.UnBound");
        public final static String Bound = get("SoulBound.Bound");
        public final static Boolean OpLock = getConfig().getBoolean("SoulBound.OpLock");
        public final static Boolean DeathDrop = getConfig().getBoolean("SoulBound.DeathDrop");
        public final static Boolean Drop = getConfig().getBoolean("SoulBound.Bind.OnItemDrop");
        public final static Boolean Pickup = getConfig().getBoolean("SoulBound.Bind.OnItemPickup");
        public final static Boolean Click = getConfig().getBoolean("SoulBound.Bind.OnItemClick");
        public final static Boolean Interact = getConfig().getBoolean("SoulBound.Bind.OnItemInteract");
    }

    public static class Gui {
        public final static String SoulBound_Title = get("Gui.SoulBound.Title");
        public final static ItemStack SoulBound_Confirm = WeiUtil.createItem
                (
                        getConfig().getString("Gui.SoulBound.Confirm.ID"),
                        getConfig().getInt("Gui.SoulBound.Confirm.Data"),
                        1,
                        getConfig().getString("Gui.SoulBound.Confirm.Name"),
                        getConfig().getStringList("Gui.SoulBound.Confirm.Lore"),
                        null
                );
        public final static ItemStack SoulBound_Other = WeiUtil.createItem
                (
                        getConfig().getString("Gui.SoulBound.Other.ID"),
                        getConfig().getInt("Gui.SoulBound.Other.Data"),
                        1,
                        null,
                        null,
                        null
                );

        public final static String Mosaic_Title = get("Gui.Mosaic.Title");
        public final static ItemStack Mosaic_Confirm = WeiUtil.createItem
                (
                        getConfig().getString("Gui.Mosaic.Confirm.ID"),
                        getConfig().getInt("Gui.Mosaic.Confirm.Data"),
                        1,
                        getConfig().getString("Gui.Mosaic.Confirm.Name"),
                        getConfig().getStringList("Gui.Mosaic.Confirm.Lore"),
                        null
                );
        public final static ItemStack Mosaic_Close = WeiUtil.createItem
                (
                        getConfig().getString("Gui.Mosaic.Close.ID"),
                        getConfig().getInt("Gui.Mosaic.Close.Data"),
                        1,
                        getConfig().getString("Gui.Mosaic.Close.Name"),
                        getConfig().getStringList("Gui.Mosaic.Close.Lore"),
                        null
                );
        public final static ItemStack Mosaic_Other = WeiUtil.createItem
                (
                        getConfig().getString("Gui.Mosaic.Other.ID"),
                        getConfig().getInt("Gui.Mosaic.Other.Data"),
                        1,
                        null,
                        null,
                        null
                );
    }
}
