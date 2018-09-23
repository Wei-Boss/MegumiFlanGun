package com.weiboss.megumi.megumiflangun.command.sub;

import com.weiboss.megumi.megumiflangun.Main;
import com.weiboss.megumi.megumiflangun.command.WeiCommand;
import com.weiboss.megumi.megumiflangun.constructor.Template;
import com.weiboss.megumi.megumiflangun.file.Config;
import com.weiboss.megumi.megumiflangun.file.Message;
import com.weiboss.megumi.megumiflangun.util.TemplateUtil;
import com.weiboss.megumi.megumiflangun.util.WeiUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetCommand extends WeiCommand {
    private Main plugin = Main.getInstance();

    @Override
    public void perform(CommandSender CommandSender, String[] Strings) {
        if (Strings.length != 3) return;
        Player p = getPlayer();
        String id = Strings[1];
        String i = Strings[2];
        if (!plugin.getMyPluginManager().getTemplateMap().containsKey(id)) {
            CommandSender.sendMessage(Config.Prefix + Message.InvalidTemplate);
            return;
        }
        if (!WeiUtil.isNumber(i)) {
            CommandSender.sendMessage(Config.Prefix + Message.InvalidValue.replace("%s%", i));
            return;
        }
        int amount = Integer.parseInt(i);
        Template template = plugin.getMyPluginManager().getTemplateMap().get(id);
        ItemStack item = TemplateUtil.getItem(template, amount);
        p.getInventory().addItem(item);
    }

    @Override
    public boolean playerOnly() {
        return true;
    }

    @Override
    public String getPermission() {
        return "megumi.admin";
    }
}
