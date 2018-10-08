package com.weiboss.megumi.megumiitem.command.sub;

import com.weiboss.megumi.megumiitem.Main;
import com.weiboss.megumi.megumiitem.command.WeiCommand;
import com.weiboss.megumi.megumiitem.file.Config;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends WeiCommand {
    private Main plugin = Main.getInstance();

    @Override
    public void perform(CommandSender CommandSender, String[] Strings) {
        plugin.getFileManager().init();
        plugin.getFileManager().initFolder();
        plugin.getMyPluginManager().init();
        CommandSender.sendMessage(Config.Prefix + "§aSuccess Reload");
    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public String getPermission() {
        return "megumi.admin";
    }
}
