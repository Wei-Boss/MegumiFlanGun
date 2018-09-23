package com.weiboss.megumi.megumiflangun.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MegumiVampireEvent extends Event {
    private static final HandlerList handlerList = new HandlerList();

    private LivingEntity attacker;
    private LivingEntity victim;
    private double vampire;
    private boolean cancel;

    public MegumiVampireEvent(LivingEntity attacker, LivingEntity victim, double vampire) {
        this.attacker = attacker;
        this.victim = victim;
        this.vampire = vampire;
        this.cancel = false;
    }

    public LivingEntity getAttacker() {
        return attacker;
    }

    public LivingEntity getVictim() {
        return victim;
    }

    public double getVampire() {
        return vampire;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setVampire(double vampire) {
        this.vampire = vampire;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
