package com.weiboss.megumi.megumiitem.gui;

import com.weiboss.megumi.megumiitem.Main;
import com.weiboss.megumi.megumiitem.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MosaicGui {
    private Main plugin;
    private Inventory gui;

    public MosaicGui(Main plugin) {
        this.plugin = plugin;
    }

    public void open(Player p, ItemStack item) {
        gui = Bukkit.createInventory(null, 54, Config.Gui.Mosaic_Title);
        addContent();
        gui.setItem(13, item);
        p.openInventory(gui);
    }

    private void addContent() {
        List<Integer> list = Arrays.asList(13, 28, 29, 30, 31, 32, 33, 34, 47, 51);
        for (int size = 0; size < 54; size++) {
            if (list.contains(size)) continue;
            gui.setItem(size, Config.Gui.Mosaic_Other);
        }
        gui.setItem(47, Config.Gui.SoulBound_Confirm);
        gui.setItem(51, Config.Gui.Mosaic_Close);
    }
}
