package com.weiboss.megumi.megumiflangun;

import com.weiboss.megumi.megumiflangun.command.MainCommand;
import com.weiboss.megumi.megumiflangun.file.FileManager;
import com.weiboss.megumi.megumiflangun.file.Message;
import com.weiboss.megumi.megumiflangun.flan.MyPluginManager;
import com.weiboss.megumi.megumiflangun.gui.GuiManager;
import com.weiboss.megumi.megumiflangun.listener.EventManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;
    private FileManager fileManager;
    private MyPluginManager myPluginManager;
    private GuiManager guiManager;
    private EventManager eventManager;

    @EventHandler
    public void onEnable() {
        instance = this;
        if (Bukkit.getPluginManager().getPlugin("HolographicDisplays") == null) {
            getLogger().info("Hook: Associated holographicDisplays failed");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        fileManager = new FileManager(this);
        myPluginManager = new MyPluginManager(this);
        guiManager = new GuiManager(this);
        eventManager = new EventManager(this);
        fileManager.init();
        fileManager.initFolder();
        myPluginManager.init();
        eventManager.init();
        getCommand("gun").setExecutor(new MainCommand());
        getLogger().info("Loaded successfully");
    }

    @EventHandler
    public void onDisable() {
        getLogger().info("Uninstall successfully");
    }

    public static Main getInstance() {
        return instance;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public MyPluginManager getMyPluginManager() {
        return myPluginManager;
    }

    public GuiManager getGuiManager() {
        return guiManager;
    }

    public String getVersion() {
        String packet = Bukkit.getServer().getClass().getPackage().getName();
        return packet.substring(packet.lastIndexOf('.') + 1);
    }
}
