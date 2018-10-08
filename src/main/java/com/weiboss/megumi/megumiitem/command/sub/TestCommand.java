package com.weiboss.megumi.megumiitem.command.sub;

import com.weiboss.megumi.megumiitem.command.WeiCommand;
import com.weiboss.megumi.megumiitem.core.data.AttrData;
import com.weiboss.megumi.megumiitem.core.data.MegumiItem;
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
            MegumiItem megumiItem = new MegumiItem(item);
            AttrData data = megumiItem.getAttrData();
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
