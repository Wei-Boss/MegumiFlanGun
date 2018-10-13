package com.weiboss.megumi.megumiitem.listener.gui;

import com.weiboss.megumi.megumiitem.file.Config;
import com.weiboss.megumi.megumiitem.file.Message;
import com.weiboss.megumi.megumiitem.util.MegumiUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SoulBoundListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onSoulBound(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player p = (Player) e.getWhoClicked();
        Inventory gui = e.getInventory();
        ItemStack item = e.getCurrentItem();
        if (item == null ||item.getItemMeta() == null) return;
        if (gui.getTitle().equalsIgnoreCase(Config.Gui.SoulBound_Title)) {
            e.setCancelled(true);
            if (item.equals(Config.Gui.SoulBound_Confirm)) {
                ItemStack bindItem = gui.getItem(11);
                int size = 0;
                for (ItemStack invItem : p.getInventory().getContents()) {
                    if (invItem == null || invItem.getItemMeta() == null) {
                        size ++;
                        continue;
                    }
                    if (invItem.equals(bindItem)) {
                        ItemStack air = new ItemStack(Material.AIR);
                        p.getInventory().setItem(size, air);
                        p.getInventory().addItem(MegumiUtil.bindPlayer(p, bindItem));
                        p.sendMessage(Config.Prefix + Message.SuccessBind);
                        p.closeInventory();
                        return;
                    }
                    size ++;
                }
            }
        }
    }
}
