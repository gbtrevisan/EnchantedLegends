package com.unicamp.mc322.enchantedlegends.game.card.unit;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;
import com.unicamp.mc322.enchantedlegends.game.player.Nexus;

import java.util.StringJoiner;

public class Follower extends Card {
    private int damage, health;

    public Follower(String name, int cost, int damage, int health, Effect... effects) {
        super(name, cost, effects);

        if (damage < 0) {
            throw new FollowerCreationException("Follower's damage cannot be negative!");
        }

        if (health < 0) {
            throw new FollowerCreationException("Follower's health cannot be negative!");
        }

        this.damage = damage;
        this.health = health;
    }

    @Override
    public void activate(GameState gameState) {
        super.activate(gameState);
        this.evoke(gameState);
    }

    public void combat(Follower enemy) {
        enemy.loseHealth(this.damage);
        this.loseHealth(enemy.damage);
    }

    public void attack(Nexus enemyNexus) {
        enemyNexus.receiveDamage(this.damage);
    }

    public void loseDamage(int amount) {
        checkAmount(amount);
        this.damage = Math.min(damage - amount, 0);
    }

    public void increaseDamage(int amount) {
        checkAmount(amount);
        this.damage += amount;
    }

    public void loseHealth(int amount) {
        checkAmount(amount);
        this.health = Math.min(health - amount, 0);
    }

    public void increaseHealth(int amount) {
        checkAmount(amount);
        this.health += amount;
    }

    private void checkAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("The amount must not be negative!");
        }
    }

    private void evoke(GameState gameState) {
        gameState.getSelf().addToEvokedUnits(this);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Follower.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add("damage=" + damage)
                .add("health=" + health)
                .toString();
    }
}
