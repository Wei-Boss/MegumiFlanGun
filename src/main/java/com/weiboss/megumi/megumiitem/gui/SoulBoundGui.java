package com.weiboss.megumi.megumiitem.gui;

import com.weiboss.megumi.megumiitem.Main;
import com.weiboss.megumi.megumiitem.file.Config;
import com.weiboss.megumi.megumiitem.util.FlanGunUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class SoulBoundGui {
    private Main plugin;
    private Inventory gui;

    public SoulBoundGui(Main plugin) {
        this.plugin = plugin;
    }

    public void open(Player p, ItemStack item) {
        gui = Bukkit.createInventory(p, 27, Config.Gui.SoulBound_Title);
        addContent();
        gui.setItem(11, item);
        gui.setItem(15, getPreview(p, item));
        gui.setItem(22, Config.Gui.SoulBound_Confirm);
        p.openInventory(gui);
    }

    private void addContent() {
        int size = 26;
        do {
            gui.setItem(size, Config.Gui.SoulBound_Other);
            size--;
        }
        while (size >= 0);
    }

    private ItemStack getPreview(Player p, ItemStack item) {
        ItemStack preview = FlanGunUtil.bindPlayer(p, item);
        ItemMeta meta = preview.getItemMeta();
        meta.setDisplayName(Config.PreviewDisplay);
        preview.setItemMeta(meta);
        return preview;
    }
}
