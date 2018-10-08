package com.weiboss.megumi.megumiflangun.file;

import com.weiboss.megumi.megumiflangun.Main;
import com.weiboss.megumi.megumiflangun.util.WeiUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Config {

    private static YamlConfiguration getConfig() {
        return Main.getInstance().getFileManager().getConfig();
    }

    public final static String Prefix = WeiUtil.onReplace(getConfig().getString("Prefix"));
    public final static String PreviewDisplay = WeiUtil.onReplace(getConfig().getString("Config.PreviewDisplay"));
    public final static List<String> CmdBlackList = getConfig().getStringList("Config.TransferableBlackList");

    public static class Vampire {
        public final static Boolean Enable = getConfig().getBoolean("Config.Vampire.Enable");
        public final static Integer Time = getConfig().getInt("Config.Vampire.Time");
        public final static Integer Amount = getConfig().getInt("Config.Vampire.Amount");
        public final static Integer MaxValue = getConfig().getInt("Config.Vampire.MaxValue");
    }

    public static class Lore {
        public final static String NotTransferable = WeiUtil.onReplace(getConfig().getString("Config.Lore.NotTransferable"));
        public final static String LevelLimit = WeiUtil.onReplace(getConfig().getString("Config.Lore.LevelLimit"));
        public final static String StatTrak = WeiUtil.onReplace(getConfig().getString("Config.Lore.StatTrak"));
        public final static String StatTrakStat = WeiUtil.onReplace(getConfig().getString("Config.Lore.StatTrakStat"));
        public final static String StatTrakTag = WeiUtil.onReplace(getConfig().getString("Config.Lore.StatTrakTag"));
        public final static String WearValue = WeiUtil.onReplace(getConfig().getString("Config.Lore.WearValue"));
    }

    public static class Attr {
        public final static String Prefix = WeiUtil.onReplace(getConfig().getString("Config.Attribute.Prefix"));
        public final static String Part = WeiUtil.onReplace(getConfig().getString("Config.Attribute.Part"));
        public final static String Positive = WeiUtil.onReplace(getConfig().getString("Config.Attribute.Positive"));
        public final static String Negative = WeiUtil.onReplace(getConfig().getString("Config.Attribute.Negative"));
        public final static String Multiply = WeiUtil.onReplace(getConfig().getString("Config.Attribute.Multiply"));
        public final static String Rate = WeiUtil.onReplace(getConfig().getString("Config.Attribute.Rate"));
    }

    public static class SoulBound {
        public final static String Unbound = WeiUtil.onReplace(getConfig().getString("SoulBound.UnBound"));
        public final static String Bound = WeiUtil.onReplace(getConfig().getString("SoulBound.Bound"));
        public final static Boolean OpLock = getConfig().getBoolean("SoulBound.OpLock");
        public final static Boolean DeathDrop = getConfig().getBoolean("SoulBound.DeathDrop");
        public final static Boolean Drop = getConfig().getBoolean("SoulBound.Bind.OnItemDrop");
        public final static Boolean Pickup = getConfig().getBoolean("SoulBound.Bind.OnItemPickup");
        public final static Boolean Click = getConfig().getBoolean("SoulBound.Bind.OnItemClick");
        public final static Boolean Interact = getConfig().getBoolean("SoulBound.Bind.OnItemInteract");
    }

    public static class Gui {
        public final static String SoulBound_Title = WeiUtil.onReplace(getConfig().getString("Gui.SoulBound.Title"));
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
    }
}
