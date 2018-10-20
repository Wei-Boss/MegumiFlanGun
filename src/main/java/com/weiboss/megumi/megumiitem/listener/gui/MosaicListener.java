package com.weiboss.megumi.megumiitem.listener.gui;

import com.weiboss.megumi.megumiitem.file.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MosaicListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player p = (Player) e.getWhoClicked();
        Inventory gui = e.getInventory();
        ItemStack item = e.getCurrentItem();
        if (item == null ||item.getItemMeta() == null) return;
        if (!gui.getTitle().equalsIgnoreCase(Config.Gui.Mosaic_Title)) return;
        int clickSlot = e.getSlot();
        List<Integer> list = Arrays.asList(29, 30, 31, 32, 33, 34);
        if (!list.contains(clickSlot) || clickSlot > 53) e.setCancelled(true);
        if (item.equals(Config.Gui.getButton("Gui.Mosaic.Close", 1))) {
            e.setCancelled(true);
            p.closeInventory();
            return;
        }
        if (item.equals(Config.Gui.getButton("Gui.Mosaic.Confirm", 1))) {

        }
    }
}
