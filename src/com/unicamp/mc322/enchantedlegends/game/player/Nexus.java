package com.unicamp.mc322.enchantedlegends.game.player;

public class Nexus {
    private int health;
    private static final int MAX_HEALTH = 20;

    public Nexus() {
        this.health = MAX_HEALTH;
    }

    public void receiveDamage(int amount) {
        this.health = Math.max(health - amount, 0);
    }
}
