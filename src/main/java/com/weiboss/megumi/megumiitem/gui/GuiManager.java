package com.weiboss.megumi.megumiitem.gui;

import com.weiboss.megumi.megumiitem.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GuiManager {
    private SoulBoundGui soulbound;

    public GuiManager(Main plugin) {
        this.soulbound = new SoulBoundGui(plugin);
    }

    public void openSoulBound(Player p, ItemStack item) {
        soulbound.open(p, item);
    }
}
