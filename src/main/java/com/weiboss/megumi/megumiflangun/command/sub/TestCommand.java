package com.weiboss.megumi.megumiflangun.command.sub;

import com.weiboss.megumi.megumiflangun.command.WeiCommand;
import com.weiboss.megumi.megumiflangun.flan.data.AttrData;
import com.weiboss.megumi.megumiflangun.flan.data.FlanGun;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestCommand extends WeiCommand {
    @Override
    public void perform(CommandSender CommandSender, String[] Strings) {
        Player p = getPlayer();
        String type = Strings[1];
        if (type.equalsIgnoreCase("hand")) {
            ItemStack item = p.getInventory().getItemInHand();
            if (item == null || item.getItemMeta() == null) return;
            FlanGun flanGun = new FlanGun(item);
            AttrData data = flanGun.getAttrData();
            data.showInfo();
        }
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
