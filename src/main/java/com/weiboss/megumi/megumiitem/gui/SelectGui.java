package com.weiboss.megumi.megumiitem.gui;

import com.weiboss.megumi.megumiitem.Main;
import com.weiboss.megumi.megumiitem.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SelectGui {
    private Main plugin;
    private Inventory gui;

    public SelectGui(Main plugin) {
        this.plugin = plugin;
    }

    public void open(Player p) {
        gui = Bukkit.createInventory(null, 54, Config.Gui.Select_Title);
        addContent();
    }

    private void addContent() {
        int[] other = {0, 1, 7, 8, 9, 36, 44, 45, 46, 52, 53};
        for (int i : other) gui.setItem(i, Config.Gui.getButton("Gui.Select.Other", 1));
        gui.setItem(49, Config.Gui.getButton("Gui.Select.Close", 1));
    }
}

