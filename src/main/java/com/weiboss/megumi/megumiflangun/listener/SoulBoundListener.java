package com.weiboss.megumi.megumiflangun.listener;

import com.weiboss.megumi.megumiflangun.Main;
import com.weiboss.megumi.megumiflangun.file.Config;
import com.weiboss.megumi.megumiflangun.file.Message;
import com.weiboss.megumi.megumiflangun.flan.data.FlanGun;
import com.weiboss.megumi.megumiflangun.task.CheckEquipTask;
import com.weiboss.megumi.megumiflangun.task.OpenSoulGuiTask;
import com.weiboss.megumi.megumiflangun.util.FlanGunUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SoulBoundListener implements Listener {
    private Main plugin = Main.getInstance();

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        if (!Config.SoulBound.Pickup) return;
        if (Config.SoulBound.OpLock && p.isOp()) return;
        ItemStack item = e.getItem().getItemStack();
        FlanGun flanGun = new FlanGun(item);
        if (flanGun.getBindUser() == null) return;
        if (flanGun.getBindUser().equalsIgnoreCase(p.getName())) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (p.isDead()) return;
        if (!Config.SoulBound.Drop) return;
        if (Config.SoulBound.OpLock && p.isOp()) return;
        ItemStack item = e.getItemDrop().getItemStack();
        FlanGun flanGun = new FlanGun(item);
        if (flanGun.getBindUser() == null) return;
        if (flanGun.getBindUser().equalsIgnoreCase(p.getName())) return;
        p.sendMessage(Config.Prefix + Message.UnableDrop);
        e.setCancelled(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player p = (Player) e.getWhoClicked();
        Inventory gui = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();
        if (item == null || item.getItemMeta() == null) return;
        FlanGun flanGun = new FlanGun(item);
        if (gui.getType() == InventoryType.PLAYER) {
            if (e.getClick() == ClickType.RIGHT) {
                if (!flanGun.getBind()) return;
                e.setCancelled(true);
                p.closeInventory();
                OpenSoulGuiTask task = new OpenSoulGuiTask(plugin, p, item);
                task.runTaskLater(plugin, 1);
                return;
            }
        }
        if (!Config.SoulBound.Click) return;
        if (Config.SoulBound.OpLock && p.isOp()) return;
        if (flanGun.getBindUser() == null) return;
        if (flanGun.getBindUser().equalsIgnoreCase(p.getName())) return;
        e.setCancelled(true);
    }

    @EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        List<ItemStack> items = new ArrayList<>();
        for (ItemStack item : new ArrayList<>(e.getDrops())) {
            if (FlanGunUtil.isBindUser(item, p) && !Config.SoulBound.DeathDrop) {
                e.getDrops().remove(item);
                items.add(item);
            }
        }
        plugin.getMyPluginManager().getSoulboundItem().put(p, items);
    }

    @EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (!plugin.getMyPluginManager().getSoulboundItem().containsKey(p)) return;
        List<ItemStack> items = plugin.getMyPluginManager().getSoulboundItem().get(p);
        for (ItemStack item : items) {
            p.getInventory().addItem(item);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) return;
        Player p = (Player) e.getPlayer();
        Inventory gui = e.getInventory();
        if (gui.getType() != InventoryType.CRAFTING) return;
        check(p);
    }

    @EventHandler
    public void onHeld(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        check(p);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();
        if (item == null || item.getItemMeta() == null) return;
        check(p);
    }

    private void check(Player p) {
        CheckEquipTask task = new CheckEquipTask(p);
        task.runTaskLaterAsynchronously(plugin, 1);
    }
}
