package com.unicamp.mc322.enchantedlegends.game.card.unit;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.card.mana.Mana;
import com.unicamp.mc322.enchantedlegends.game.card.trait.Trait;
import com.unicamp.mc322.enchantedlegends.game.card.trait.TraitException;
import com.unicamp.mc322.enchantedlegends.game.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;
import com.unicamp.mc322.enchantedlegends.game.player.Nexus;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Follower extends Card {
    private int initialHealth;
    protected int damage;
    protected int health;
    private List<Trait> traits;

    public Follower() {
    }

    public Follower(String name, int cost, int damage, int health, Trait trait, Effect... effects) {
        super(name, cost, effects);

        if (damage < 0) {
            throw new FollowerCreationException("Follower's damage cannot be negative!");
        }

        if (health < 0) {
            throw new FollowerCreationException("Follower's health cannot be negative!");
        }

        this.damage = damage;
        this.health = this.initialHealth = health;
        this.traits = new ArrayList<>();
        this.traits.add(trait);
    }

    public void addTrait(Trait trait) {
        this.traits.add(trait);
    }

    public List<Trait> getTraits() {
        return traits;
    }

    @Override
    public void activate(Mana mana) {
        super.activate(mana);
        this.evoke();
    }

    public boolean dontHasTrait(Trait trait) {
        return !this.traits.contains(trait);
    }

    public boolean validateCombat(Follower enemy) {
        try {
            for(Trait trait: traits) {
                trait.applyIfApplicable(this, enemy);
            }

            return true;
        } catch (TraitException ex) {
            return false;
        }
    }

    public void combat(Follower enemy) throws TraitException {
        for (Trait trait : traits) {
            trait.applyIfApplicable(this, enemy);
        }

        enemy.loseHealth(this.damage);
        this.loseHealth(enemy.damage);

        for (Trait trait : traits) {
            trait.applyIfApplicable(this, enemy);
        }
    }

    public void attackNexus(Nexus enemyNexus) {
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

    public boolean isDead() {
        return this.health == 0;
    }

    public void buff(int extraDamage, int extraHealth) {
        checkAmount(extraDamage);
        checkAmount(extraHealth);

        this.damage += extraDamage;
        this.health += extraHealth;
    }

    public int getDamage() {
        return damage;
    }

    private void checkAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("The amount must not be negative!");
        }
    }

    private void evoke() {
        GameState.getInstance().getSelf().addToEvokedUnits(this);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Follower.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add("damage=" + damage)
                .add("health=" + health)
                .add("initialHealth=" + initialHealth)
                .add("traits=" + traits)
                .toString();
    }
}
