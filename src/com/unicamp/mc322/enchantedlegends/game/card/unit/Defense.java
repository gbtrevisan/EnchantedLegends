package com.unicamp.mc322.enchantedlegends.game.card.unit;

public class Defense {

    private final int maxHealth;
    private int currentHealth;
    private boolean barrier;

    Defense(int maxHealth) {
        if (maxHealth <= 0) {
            throw new IllegalArgumentException("Defense health should be greater then 0");
        }

        this.maxHealth = this.currentHealth = maxHealth;
        barrier = false;
    }

    void loseHealth(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Damage amount should be greater or equal to 0");
        }

        if (barrier && amount != 0) {
            barrier = false;
        } else {
            currentHealth = Math.max(currentHealth - amount, 0);
        }
    }

    void healHealth(int amount) {
        currentHealth = Math.min(currentHealth + amount, maxHealth);
    }

    void activateBarrier() {
        barrier = true;
    }

    boolean isDead() {
        return currentHealth == 0;
    }

    @Override
    public String toString() {
        return "maxHealth= " + maxHealth + "\n" + "currentHealth= " + currentHealth;
    }
}
