package com.weiboss.megumi.megumiitem.listener;

import com.weiboss.megumi.megumiitem.file.Config;
import com.weiboss.megumi.megumiitem.file.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NoTransferableCmdListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInHand();
        if (item == null || item.getItemMeta() == null) return;
        ItemMeta meta = item.getItemMeta();
        if (meta.getLore() == null) return;
        if (!meta.getLore().contains(Config.Lore.NotTransferable)) return;
        String cmd = e.getMessage();
        for (String s : Config.CmdBlackList) {
            if (cmd.startsWith(s)) {
                e.setCancelled(true);
                p.sendMessage(Config.Prefix + Message.NoTransferable);
                return;
            }
        }
    }
}
