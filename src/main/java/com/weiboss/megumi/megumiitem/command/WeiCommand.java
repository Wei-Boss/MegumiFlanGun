package com.weiboss.megumi.megumiitem.command;

import com.weiboss.megumi.megumiitem.file.Config;
import com.weiboss.megumi.megumiitem.file.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class WeiCommand {
    private boolean isPlayer;
    private Player player;

    public final void execute(CommandSender commandSender, String[] strings) {
        this.isPlayer = commandSender instanceof Player;
        if (isPlayer) player = (Player) commandSender;
        if (playerOnly() && !isPlayer) return;
        if (getPermission() != null && !commandSender.hasPermission(getPermission())) {
            commandSender.sendMessage(Config.Prefix + Message.NoPermission.replace("%s%",getPermission()));
            return;
        }
        perform(commandSender, strings);
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract void perform(CommandSender CommandSender, String[] Strings);

    public abstract boolean playerOnly();

    public abstract String getPermission();
}
