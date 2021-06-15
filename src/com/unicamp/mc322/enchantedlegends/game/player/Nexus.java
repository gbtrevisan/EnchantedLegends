package com.unicamp.mc322.enchantedlegends.game.player;

public class Nexus {
    private int health;
    private static int MAX_HEALTH = 20;

    public void receiveDamage(int amount) {
        this.health = Math.min(health - amount, 0);
    }
}
