package com.weiboss.megumi.megumiitem.listener;

import com.weiboss.megumi.megumiitem.Main;
import com.weiboss.megumi.megumiitem.listener.gui.SoulBoundListener;
import org.bukkit.Bukkit;

public class EventManager {
    private Main plugin;

    public EventManager(Main plugin) {
        this.plugin = plugin;
    }

    public void init() {
        Bukkit.getPluginManager().registerEvents(new DamageListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new NoTransferableCmdListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new com.weiboss.megumi.megumiitem.listener.SoulBoundListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new SoulBoundListener(), plugin);
    }
}
