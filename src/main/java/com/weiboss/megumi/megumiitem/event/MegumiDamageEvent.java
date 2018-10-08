package com.weiboss.megumi.megumiitem.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MegumiDamageEvent extends Event {
    private static final HandlerList handlerList = new HandlerList();

    private LivingEntity attacker;
    private LivingEntity victim;
    private double defaultDamage;
    private double attrDamage;
    private boolean dodge;
    private boolean crit;
    private boolean block;
    private boolean cancel;

    public MegumiDamageEvent(LivingEntity attacker, LivingEntity victim, double defaultDamage, double attrDamage, boolean dodge, boolean crit, boolean block) {
        this.attacker = attacker;
        this.victim = victim;
        this.defaultDamage = defaultDamage;
        this.attrDamage = attrDamage;
        this.dodge = dodge;
        this.crit = crit;
        this.block = block;
        this.cancel = false;
    }

    public LivingEntity getAttacker() {
        return attacker;
    }

    public LivingEntity getVictim() {
        return victim;
    }

    public double getDefaultDamage() {
        return defaultDamage;
    }

    public double getAttrDamage() {
        return attrDamage;
    }

    public boolean isDodge() {
        return dodge;
    }

    public boolean isCrit() {
        return crit;
    }

    public boolean isBlock() {
        return block;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setDefaultDamage(double defaultDamage) {
        this.defaultDamage = defaultDamage;
    }

    public void setAttrDamage(double attrDamage) {
        this.attrDamage = attrDamage;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public void setCrit(boolean crit) {
        this.crit = crit;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
