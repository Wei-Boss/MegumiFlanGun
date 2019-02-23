package com.weiboss.megumi.megumiitem;

import com.weiboss.megumi.megumiitem.command.MainCommand;
import com.weiboss.megumi.megumiitem.file.FileManager;
import com.weiboss.megumi.megumiitem.core.MyPluginManager;
import com.weiboss.megumi.megumiitem.gui.GuiManager;
import com.weiboss.megumi.megumiitem.listener.EventManager;
import com.weiboss.megumi.megumiitem.command.MainComplete;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Getter private static Main instance;
    @Getter private FileManager fileManager;
    @Getter private MyPluginManager myPluginManager;
    @Getter private GuiManager guiManager;
    @Getter private EventManager eventManager;

    @EventHandler
    public void onEnable() {
        instance = this;
        fileManager = new FileManager(this);
        myPluginManager = new MyPluginManager(this);
        guiManager = new GuiManager(this);
        eventManager = new EventManager(this);
        fileManager.init();
        fileManager.initFolder();
        myPluginManager.init();
        eventManager.init();
        getCommand("mitem").setExecutor(new MainCommand());
        getCommand("mitem").setTabCompleter(new MainComplete(this));
        getLogger().info("Loaded successfully");
    }

    @EventHandler
    public void onDisable() {
        getLogger().info("Uninstall successfully");
    }

    public String getVersion() {
        String packet = Bukkit.getServer().getClass().getPackage().getName();
        return packet.substring(packet.lastIndexOf('.') + 1);
    }
}
