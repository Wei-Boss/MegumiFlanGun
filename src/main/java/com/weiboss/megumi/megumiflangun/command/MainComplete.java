package com.weiboss.megumi.megumiflangun.command;

import com.weiboss.megumi.megumiflangun.Main;
import com.weiboss.megumi.megumiflangun.tadokoro.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainComplete implements TabCompleter {
    private Main plugin;
    private List<String> subCommand;
    private HashMap<String, List<String>> commands;

    public MainComplete(Main plugin) {
        this.plugin = plugin;
        this.subCommand = Arrays.asList("attr", "get", "give");
        this.commands = new HashMap<>();
        this.commands.put("attr", Attribute.getAttribute());
        this.commands.put("get", new ArrayList<>(plugin.getMyPluginManager().getTemplateMap().keySet()));
        this.commands.put("give", new ArrayList<>(plugin.getMyPluginManager().getTemplateMap().keySet()));
    }
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 3) return new ArrayList<>();
        if (strings.length == 1) return subCommand;
        if (strings.length == 2) {
            String sub = strings[0];
            if (commands.containsKey(sub)) return commands.get(sub);
        }
        return new ArrayList<>();
    }
}
