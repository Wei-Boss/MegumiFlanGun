package com.weiboss.megumi.megumiflangun.flan.data;

public class AttrData {
    private double health;
    private double damage;
    private double defense;
    private double ignoreDamage;
    private double ignoreDefense;
    private double vampire;
    private double hitChance;
    private double dodgeChance;
    private double wreckChance;
    private double blockChance;
    private double blockRate;
    private double critChance;
    private double critRate;
    private double antiKnockChance;
    private double movementSpeed;

    public AttrData() {
        this.health = 0d;
        this.damage = 0d;
        this.defense = 0d;
        this.ignoreDamage = 0d;
        this.ignoreDefense = 0d;
        this.vampire = 0d;
        this.hitChance = 0d;
        this.dodgeChance = 0d;
        this.wreckChance = 0d;
        this.blockChance = 0d;
        this.blockRate = 0d;
        this.critChance = 0d;
        this.critRate = 0d;
        this.antiKnockChance = 0d;
        this.movementSpeed = 0d;
    }

    public AttrData(double health,
                    double damage,
                    double defense,
                    double ignoreDamage,
                    double ignoreDefense,
                    double vampire,
                    double hitChance,
                    double dodgeChance,
                    double wreckChance,
                    double blockChance,
                    double blockRate,
                    double critChance,
                    double critRate,
                    double antiKnockChance,
                    double movementSpeed) {
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.ignoreDamage = ignoreDamage;
        this.ignoreDefense = ignoreDefense;
        this.vampire = vampire;
        this.hitChance = hitChance;
        this.dodgeChance = dodgeChance;
        this.wreckChance = wreckChance;
        this.blockChance = blockChance;
        this.blockRate = blockRate;
        this.critChance = critChance;
        this.critRate = critRate;
        this.antiKnockChance = antiKnockChance;
        this.movementSpeed = movementSpeed;
    }

    public void addHealth(double health) {
        this.health += health;
    }

    public void addDamage(double damage) {
        this.damage += damage;
    }

    public void addDefense(double defense) {
        this.defense += defense;
    }

    public void addIgnoreDamage(double ignoreDamage) {
        this.ignoreDamage += ignoreDamage;
    }

    public void addIgnoreDefense(double ignoreDefense) {
        this.ignoreDefense += ignoreDefense;
    }

    public void addVampire(double vampire) {
        this.vampire += vampire;
    }

    public void addHitChance(double hitChance) {
        this.hitChance += hitChance;
    }

    public void addDodgeChance(double dodgeChance) {
        this.dodgeChance += dodgeChance;
    }

    public void addWreckChance(double wreckChance) {
        this.wreckChance += wreckChance;
    }

    public void addBlockChance(double blockChance) {
        this.blockChance += blockChance;
    }

    public void addBlockRate(double blockRate) {
        this.blockRate += blockRate;
    }

    public void addCritChance(double critChance) {
        this.critChance += critChance;
    }

    public void addCritRate(double critRate) {
        this.critRate += critRate;
    }

    public void addAntiKnockChance(double antiKnockChance) {
        this.antiKnockChance += antiKnockChance;
    }

    public void addMovementSpeed(double movementSpeed) {
        this.movementSpeed += movementSpeed;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public void setIgnoreDamage(double ignoreDamage) {
        this.ignoreDamage = ignoreDamage;
    }

    public void setIgnoreDefense(double ignoreDefense) {
        this.ignoreDefense = ignoreDefense;
    }

    public void setVampire(double vampire) {
        this.vampire = vampire;
    }

    public void setHitChance(double hitChance) {
        this.hitChance = hitChance;
    }

    public void setDodgeChance(double dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public void setWreckChance(double wreckChance) {
        this.wreckChance = wreckChance;
    }

    public void setBlockChance(double blockChance) {
        this.blockChance = blockChance;
    }

    public void setBlockRate(double blockRate) {
        this.blockRate = blockRate;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public void setCritRate(double critRate) {
        this.critRate = critRate;
    }

    public void setAntiKnockChance(double antiKnockChance) {
        this.antiKnockChance = antiKnockChance;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public double getHealth() {
        return health;
    }

    public double getDamage() {
        return damage;
    }

    public double getDefense() {
        return defense;
    }

    public double getIgnoreDamage() {
        return ignoreDamage;
    }

    public double getIgnoreDefense() {
        return ignoreDefense;
    }

    public double getVampire() {
        return vampire;
    }

    public double getHitChance() {
        return hitChance;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public double getWreckChance() {
        return wreckChance;
    }

    public double getBlockChance() {
        return blockChance;
    }

    public double getBlockRate() {
        return blockRate;
    }

    public double getCritChance() {
        return critChance;
    }

    public double getCritRate() {
        return critRate;
    }

    public double getAntiKnockChance() {
        return antiKnockChance;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }
}
