package com.unicamp.mc322.enchantedlegends.game.card.unit;

public class Defense {

    private final int maxHealth;
    private int currentHealth;
    private boolean hasBarrier;

    Defense(int maxHealth) {
        if (maxHealth <= 0) {
            throw new IllegalArgumentException("Defense health should be greater then 0");
        }

        this.maxHealth = this.currentHealth = maxHealth;
        hasBarrier = false;
    }

    void loseHealth(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Damage amount should be greater or equal to 0");
        }

        if (hasBarrier && amount != 0) {
            hasBarrier = false;
        } else {
            currentHealth = Math.max(currentHealth - amount, 0);
        }
    }

    void healHealth(int amount) {
        currentHealth = Math.min(currentHealth + amount, maxHealth);
    }

    void activateBarrier() {
        hasBarrier = true;
    }

    boolean isDead() {
        return currentHealth == 0;
    }

    @Override
    public String toString() {
        return "maxHealth= " + maxHealth + "\n" + "currentHealth= " + currentHealth;
    }
}
