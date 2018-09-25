package com.weiboss.megumi.megumiflangun.file;

import com.weiboss.megumi.megumiflangun.Main;
import com.weiboss.megumi.megumiflangun.util.WeiUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Config {
    private static YamlConfiguration config = Main.getInstance().getFileManager().getConfig();

    public static void initConfig() {
        config = Main.getInstance().getFileManager().getMessage();
    }

    public final static String Prefix = WeiUtil.onReplace(config.getString("Prefix"));
    public final static String PreviewDisplay = WeiUtil.onReplace(config.getString("Config.PreviewDisplay"));
    public final static Integer MaxVampire = config.getInt("Config.MaxVampire");
    public final static List<String> CmdBlackList = config.getStringList("Config.TransferableBlackList");

    public static class Lore {
        public final static String NotTransferable = WeiUtil.onReplace(config.getString("Config.Lore.NotTransferable"));
        public final static String LevelLimit = WeiUtil.onReplace(config.getString("Config.Lore.LevelLimit"));
        public final static String StatTrak = WeiUtil.onReplace(config.getString("Config.Lore.StatTrak"));
        public final static String StatTrakStat = WeiUtil.onReplace(config.getString("Config.Lore.StatTrakStat"));
        public final static String StatTrakTag = WeiUtil.onReplace(config.getString("Config.Lore.StatTrakTag"));
        public final static String WearValue = WeiUtil.onReplace(config.getString("Config.Lore.WearValue"));
    }

    public static class Attr {
        public final static String Prefix = WeiUtil.onReplace(config.getString("Config.Attribute.Prefix"));
        public final static String Part = WeiUtil.onReplace(config.getString("Config.Attribute.Part"));
        public final static String Positive = WeiUtil.onReplace(config.getString("Config.Attribute.Positive"));
        public final static String Negative = WeiUtil.onReplace(config.getString("Config.Attribute.Negative"));
        public final static String Multiply = WeiUtil.onReplace(config.getString("Config.Attribute.Multiply"));
        public final static String Rate = WeiUtil.onReplace(config.getString("Config.Attribute.Rate"));
    }

    public static class SoulBound {
        public final static String Unbound = WeiUtil.onReplace(config.getString("SoulBound.UnBound"));
        public final static String Bound = WeiUtil.onReplace(config.getString("SoulBound.Bound"));
        public final static Boolean OpLock = config.getBoolean("SoulBound.OpLock");
        public final static Boolean DeathDrop = config.getBoolean("SoulBound.DeathDrop");
        public final static Boolean Drop = config.getBoolean("SoulBound.Bind.OnItemDrop");
        public final static Boolean Pickup = config.getBoolean("SoulBound.Bind.OnItemPickup");
        public final static Boolean Click = config.getBoolean("SoulBound.Bind.OnItemClick");
        public final static Boolean Interact = config.getBoolean("SoulBound.Bind.OnItemInteract");
    }

    public static class Gui {
        public final static String SoulBound_Title = WeiUtil.onReplace(config.getString("Gui.SoulBound.Title"));
        public final static ItemStack SoulBound_Confirm = WeiUtil.createItem
                (
                        config.getInt("Gui.SoulBound.Confirm.ID"),
                        config.getInt("Gui.SoulBound.Confirm.Data"),
                        1,
                        config.getString("Gui.SoulBound.Confirm.Name"),
                        config.getStringList("Gui.SoulBound.Confirm.Lore")
                );
        public final static ItemStack SoulBound_Other = WeiUtil.createItem
                (
                        config.getInt("Gui.SoulBound.Other.ID"),
                        config.getInt("Gui.SoulBound.Other.Data"),
                        1,
                        null,
                        null
                );
    }
}
