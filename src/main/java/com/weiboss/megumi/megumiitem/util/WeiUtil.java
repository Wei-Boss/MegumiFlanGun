package com.weiboss.megumi.megumiitem.util;

import com.weiboss.megumi.megumiitem.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class WeiUtil {
    private static Main plugin = Main.getInstance();

    public static String onReplace(String text) {
        return text
                .replace("&", "§");
    }

    public static List<String> onReplace(List<String> texts) {
        List<String> list = new ArrayList<>();
        for (String s : texts) {
            list.add(onReplace(s));
        }
        return list;
    }

    public static boolean isNumber(String a) {
        return a.matches("^[0-9]*[1-9][0-9]*$");
    }

    public static boolean isFloat(String a) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(a).matches();
    }

    public static void copyFile(InputStream inputStream, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] arrayOfByte = new byte['?'];
            int i;
            while ((i = inputStream.read(arrayOfByte)) > 0) {
                fileOutputStream.write(arrayOfByte, 0, i);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ItemStack createItem(String id, int data, int amount, String name, List<String> lore, HashMap<Enchantment, Integer> enchants) {
        ItemStack item;
        if (WeiUtil.isNumber(id)) {
            if (plugin.getVersion().startsWith("V1_13")) return new ItemStack(Material.STONE, 1);
            item = new ItemStack(Material.getMaterial(Integer.parseInt(id)), amount);
        }
        else
            item = new ItemStack(Material.getMaterial(id), amount);
        item.setDurability((short) data);
        ItemMeta meta = item.getItemMeta();
        if (name != null)
            meta.setDisplayName(onReplace(name));
        if (lore != null)
            meta.setLore(onReplace(lore));
        if (enchants != null) {
            for (Map.Entry<Enchantment, Integer> map : enchants.entrySet()) {
                Enchantment enchantment = map.getKey();
                Integer level = map.getValue();
                meta.addEnchant(enchantment, level, false);
            }
        }
        item.setItemMeta(meta);
        return item;
    }

    public static void executionCmd(Player p, List<String> cmd) {
        for (String s : cmd) {
            String type = s.split(":")[0];
            String command = s.split(":")[1].replace("%player%", p.getName());
            if (type.equalsIgnoreCase("op")) {
                opCmd(p, command);
            }
            if (type.equalsIgnoreCase("console")) {
                consoleCmd(command);
            }
            if (type.equalsIgnoreCase("player")) {
                playerCmd(p, command);
            }
        }
    }

    public static void opCmd(Player p, String cmd) {
        if (p.isOp()) {
            playerCmd(p, cmd);
        } else {
            p.setOp(true);
            playerCmd(p, cmd);
            p.setOp(false);
        }
    }

    public static void consoleCmd(String cmd) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
    }

    public static void playerCmd(Player p, String cmd) {
        PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent(p, String.format("/%s", cmd));
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;

        p.performCommand(event.getMessage().replace("/", ""));
    }

}
