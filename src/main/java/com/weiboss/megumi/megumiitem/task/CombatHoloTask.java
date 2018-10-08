package com.weiboss.megumi.megumiitem.task;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.weiboss.megumi.megumiitem.Main;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class CombatHoloTask extends BukkitRunnable {
    private int i;
    private Location location;
    private Hologram hologram;

    public CombatHoloTask(Location location, String message) {
        this.i = 1;
        this.location = location;
        this.hologram = HologramsAPI.createHologram(Main.getInstance(), location);
        this.hologram.appendTextLine(message);
    }

    @Override
    public void run() {
        hologram.teleport(location.add(0d, 0.1, 0d));
        i++;
        if (i == 12) {
            hologram.delete();
            cancel();
        }
    }
}
