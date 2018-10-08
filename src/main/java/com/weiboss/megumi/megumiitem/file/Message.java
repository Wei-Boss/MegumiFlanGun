package com.weiboss.megumi.megumiitem.file;

import com.weiboss.megumi.megumiitem.Main;
import com.weiboss.megumi.megumiitem.util.WeiUtil;
import org.bukkit.configuration.file.YamlConfiguration;

public class Message {
    private static YamlConfiguration message = Main.getInstance().getFileManager().getMessage();

    public static void initMessage() {
        message = Main.getInstance().getFileManager().getMessage();
    }

    public final static String NoPermission = WeiUtil.onReplace(message.getString("Message.NoPermission"));
    public final static String InvalidTemplate = WeiUtil.onReplace(message.getString("Message.InvalidTemplate"));
    public final static String InvalidValue = WeiUtil.onReplace(message.getString("Message.InvalidValue"));
    public final static String OfflinePlayer = WeiUtil.onReplace(message.getString("Message.OfflinePlayer"));
    public final static String NeedBind = WeiUtil.onReplace(message.getString("Message.NeedBind"));
    public final static String IllegalUser = WeiUtil.onReplace(message.getString("Message.IllegalUser"));
    public final static String UnableDrop = WeiUtil.onReplace(message.getString("Message.UnableDrop"));
    public final static String SuccessBind = WeiUtil.onReplace(message.getString("Message.SuccessBind"));
    public final static String LevelLimit = WeiUtil.onReplace(message.getString("Message.LevelLimit"));
    public final static String NoTransferable = WeiUtil.onReplace(message.getString("Message.NoTransferable"));
    public final static String Kill = WeiUtil.onReplace(message.getString("Message.Kill"));

    public static class Combat {
        public final static String Damage = WeiUtil.onReplace(message.getString("Combat.Damage"));
        public final static String Dodge = WeiUtil.onReplace(message.getString("Combat.Dodge"));
        public final static String Crit = WeiUtil.onReplace(message.getString("Combat.Crit"));
        public final static String Block = WeiUtil.onReplace(message.getString("Combat.Block"));
    }

}
