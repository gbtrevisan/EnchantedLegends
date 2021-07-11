package com.unicamp.mc322.enchantedlegends.game.card.unit;

import java.util.StringJoiner;

public class Attack {

    private int damage;
    private int currentDamage;

    public Attack() {
    }

    Attack(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Attack damage should be greater or equal to 0");
        }

        this.damage = currentDamage = damage;
    }

    public int causeDamage() {
        return currentDamage;
    }

    void increaseDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to increase damage should be non negative");
        }

        this.currentDamage += amount;
    }

    void loseDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to lose damage should be non negative");
        }

        this.currentDamage = Math.max(currentDamage - amount, 0);
    }

    void annulDamage() {
        currentDamage = 0;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Attack.class.getSimpleName() + "[", "]")
                .add("damage=" + damage)
                .add("currentDamage=" + currentDamage)
                .toString();
    }
}
