package com.weiboss.megumi.megumiflangun.task;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class VampireTask extends BukkitRunnable {
    private LivingEntity entity;
    private Double vampire;
    private Integer amount;

    public VampireTask(LivingEntity entity, Double vampire, Integer amount) {
        this.entity = entity;
        this.vampire = vampire;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (amount <= 0) {
            cancel();
            return;
        }
        double heath = entity.getHealth() + (vampire / amount);
        if (heath > entity.getMaxHealth()) entity.setHealth(entity.getMaxHealth());
        else entity.setHealth(heath);
        amount--;
    }
}
