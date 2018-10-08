package com.weiboss.megumi.megumiitem.command.sub;

import com.weiboss.megumi.megumiitem.Main;
import com.weiboss.megumi.megumiitem.command.WeiCommand;
import com.weiboss.megumi.megumiitem.util.WeiUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class HelpCommand extends WeiCommand {
    private Main plugin = Main.getInstance();

    @Override
    public void perform(CommandSender CommandSender, String[] Strings) {
        YamlConfiguration message = Main.getInstance().getFileManager().getMessage();
        if (!CommandSender.hasPermission("megumi.admin")) return;
        for (String s : message.getStringList("AdminHelp")) {
            CommandSender.sendMessage(WeiUtil.onReplace(s));
        }
    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public String getPermission() {
        return "megumi.use";
    }
}
