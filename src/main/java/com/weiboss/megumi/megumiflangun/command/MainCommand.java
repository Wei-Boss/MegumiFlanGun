package com.weiboss.megumi.megumiflangun.command;

import com.weiboss.megumi.megumiflangun.command.sub.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class MainCommand implements CommandExecutor {
    private HelpCommand help;
    private HashMap<String, WeiCommand> commands;

    public MainCommand() {
        this.help = new HelpCommand();
        this.commands = new HashMap<>();
        this.commands.put("get", new GetCommand());
        this.commands.put("give", new GiveCommand());
        this.commands.put("attr", new AttrCommand());
        this.commands.put("test", new TestCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Object cmd = help;
        if (strings.length >= 1 && commands.containsKey(strings[0])) {
            cmd = commands.get(strings[0]);
        }
        ((WeiCommand) cmd).execute(commandSender, strings);
        return false;
    }
}
