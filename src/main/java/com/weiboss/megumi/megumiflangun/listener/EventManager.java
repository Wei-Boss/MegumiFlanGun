package com.weiboss.megumi.megumiflangun.listener;

import com.weiboss.megumi.megumiflangun.Main;
import org.bukkit.Bukkit;

public class EventManager {
    private Main plugin;

    public EventManager(Main plugin) {
        this.plugin = plugin;
    }

    public void init() {
        Bukkit.getPluginManager().registerEvents(new DamageListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new NoTransferableCmdListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new SoulBoundListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), plugin);
    }
}
