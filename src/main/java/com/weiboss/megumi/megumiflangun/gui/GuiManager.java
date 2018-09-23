package com.weiboss.megumi.megumiflangun.gui;

import com.weiboss.megumi.megumiflangun.Main;
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
