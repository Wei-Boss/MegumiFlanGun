package com.weiboss.megumi.megumiflangun.task;

import com.weiboss.megumi.megumiflangun.file.Config;
import com.weiboss.megumi.megumiflangun.file.Message;
import com.weiboss.megumi.megumiflangun.flan.data.FlanGun;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckEquipTask extends BukkitRunnable {
    private Player p;

    public CheckEquipTask(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        FlanGun helmet = new FlanGun(p.getInventory().getHelmet());
        FlanGun chestplate = new FlanGun(p.getInventory().getChestplate());
        FlanGun leggings = new FlanGun(p.getInventory().getLeggings());
        FlanGun boots = new FlanGun(p.getInventory().getBoots());

        check(p, helmet, 1);
        check(p, chestplate, 2);
        check(p, leggings, 3);
        check(p, boots, 4);

        FlanGun equip = new FlanGun
                (
                        p,
                        p.getItemInHand(),
                        p.getInventory().getHelmet(),
                        p.getInventory().getChestplate(),
                        p.getInventory().getLeggings(),
                        p.getInventory().getBoots()
                );
        float speed = 0.2f * (1f + (float) equip.getAttrData().getMovementSpeed());
        if (speed > 1f) speed = 0.99f;
        p.setWalkSpeed(speed);
        double health = equip.getAttrData().getHealth() + 20;
        if (p.getHealth() > health) p.setHealth(health);
        p.setMaxHealth(health);
    }

    private void check(Player p, FlanGun flanGun, int i) {
        boolean off = false;
        ItemStack itemStack = flanGun.getItem();
        if (itemStack == null || itemStack.getItemMeta() == null) return;
        String display = itemStack.getItemMeta().getDisplayName() == null ? itemStack.getType().toString() : itemStack.getItemMeta().getDisplayName();
        if (flanGun.getBind()) {
            setEquip(p, i);
            if (notFull(p)) {
                p.getInventory().addItem(itemStack);
                p.sendMessage(Config.Prefix + Message.NeedBind.replace("%item%", display));
            }
            else {
                p.getWorld().dropItem(p.getLocation(), itemStack).setPickupDelay(40);
                p.sendMessage(Config.Prefix + Message.NeedBind.replace("%item%", display));
            }
            off = true;
        }
        else if (flanGun.getBindUser() != null) {
            if (!flanGun.getBindUser().equalsIgnoreCase(p.getName())) {
                setEquip(p, i);
                p.getWorld().dropItem(p.getLocation(), itemStack).setPickupDelay(40);
                p.sendMessage(Config.Prefix + Message.IllegalUser.replace("%item%", display));
                off = true;
            }
        }
        if (flanGun.getLevelLimit() != null) {
            if (!off && flanGun.getLevelLimit() > p.getLevel()) {
                setEquip(p, i);
                if (notFull(p)) {
                    p.getInventory().addItem(itemStack);
                    p.sendMessage(Config.Prefix + Message.LevelLimit.replace("%item%", display));
                }
                else {
                    p.getWorld().dropItem(p.getLocation(), itemStack).setPickupDelay(40);
                    p.sendMessage(Config.Prefix + Message.LevelLimit.replace("%item%", display));
                }
            }
        }
    }

    private void setEquip(Player p, int i) {
        if (i == 1) p.getInventory().setHelmet(null);
        if (i == 2) p.getInventory().setChestplate(null);
        if (i == 3) p.getInventory().setLeggings(null);
        if (i == 4) p.getInventory().setBoots(null);
    }

    private boolean notFull(Player p) {
        int i = 0;
        for (ItemStack item : p.getInventory().getContents()) {
            if (item == null) i ++;
        }
        return i > 0;
    }
}
