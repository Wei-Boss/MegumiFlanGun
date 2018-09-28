package com.weiboss.megumi.megumiflangun.command.sub;

import com.weiboss.megumi.megumiflangun.Main;
import com.weiboss.megumi.megumiflangun.command.WeiCommand;
import com.weiboss.megumi.megumiflangun.constructor.Template;
import com.weiboss.megumi.megumiflangun.file.Config;
import com.weiboss.megumi.megumiflangun.file.Message;
import com.weiboss.megumi.megumiflangun.util.TemplateUtil;
import com.weiboss.megumi.megumiflangun.util.WeiUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand extends WeiCommand {
    private Main plugin = Main.getInstance();

    @Override
    public void perform(CommandSender CommandSender, String[] Strings) {
        if (Strings.length != 4) return;
        String id = Strings[1];
        String player = Strings[2];
        String amount = Strings[3];
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player);
        if (!offlinePlayer.isOnline()) {
            CommandSender.sendMessage(Config.Prefix + Message.OfflinePlayer);
            return;
        }
        if (!plugin.getMyPluginManager().getTemplateMap().containsKey(id)) {
            CommandSender.sendMessage(Config.Prefix + Message.InvalidTemplate);
            return;
        }
        if (!WeiUtil.isNumber(amount)) {
            CommandSender.sendMessage(Config.Prefix + Message.InvalidValue.replace("%s%", amount));
            return;
        }
        Player p = Bukkit.getPlayerExact(player);
        int i = Integer.parseInt(amount);
        Template template = plugin.getMyPluginManager().getTemplateMap().get(id);
        ItemStack item = TemplateUtil.getItem(template, i);
        p.getInventory().addItem(item);
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
