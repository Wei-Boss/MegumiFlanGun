package com.weiboss.megumi.megumiitem.task;

import com.weiboss.megumi.megumiitem.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class OpenSoulGuiTask extends BukkitRunnable {
    private Main plugin;
    private Player p;
    private ItemStack item;

    public OpenSoulGuiTask(Main plugin, Player p, ItemStack item) {
        this.plugin = plugin;
        this.p = p;
        this.item = item;
    }

    @Override
    public void run() {
        plugin.getGuiManager().openSoulBound(p, item);
    }
}
