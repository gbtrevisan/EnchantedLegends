package com.unicamp.mc322.enchantedlegends.game.card.unit;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.event.EventManager;
import com.unicamp.mc322.enchantedlegends.game.card.mana.Mana;
import com.unicamp.mc322.enchantedlegends.game.card.unit.trait.Trait;
import com.unicamp.mc322.enchantedlegends.game.card.unit.trait.exception.TraitException;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;
import com.unicamp.mc322.enchantedlegends.game.player.Nexus;

import java.util.StringJoiner;

public class Follower extends Card {

    protected Attack attack;
    protected final Defense defense;
    private final EventManager eventManager;

    public Follower(String name, int cost, int damage, int health, Trait trait, Effect... effects) {
        super(name, cost, effects);

        if (damage < 0) {
            throw new FollowerCreationException("Follower's damage cannot be negative!");
        }

        if (health < 0) {
            throw new FollowerCreationException("Follower's health cannot be negative!");
        }

        attack = new Attack(damage);
        defense = new Defense(health);
        eventManager = new EventManager();
        eventManager.subscribe(trait);
    }

    public void addTrait(Trait trait) {
        eventManager.subscribe(trait);
    }

    @Override
    public void activate(Mana mana) {
        super.activate(mana);
        this.evoke();
    }

    public void combat(Follower enemy) throws TraitException {
        enemy.loseHealth(this.attack.causeDamage());
        this.loseHealth(enemy.attack.causeDamage());
    }

    public void attackNexus(Nexus enemyNexus) {
        enemyNexus.receiveDamage(this.attack.causeDamage());
    }

    public void loseDamage(int amount) {
        this.attack.loseDamage(amount);
    }

    public void increaseDamage(int amount) {
        this.attack.increaseDamage(amount);
    }

    public void annulAttack() {
        attack.annulDamage();
    }

    public void loseHealth(int amount) {
        defense.loseHealth(amount);
    }

    public void increaseHealth(int amount) {
        defense.healHealth(amount);
    }

    public boolean isDead() {
        return defense.isDead();
    }

    public void buff(int damage, int health) {
        this.attack.increaseDamage(damage);
        this.defense.healHealth(health);
    }

    private void evoke() {
        GameState.getInstance().getSelf().evokeUnit(this);
    }

    public void gainBarrier() {
        defense.activateBarrier();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Follower.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add(attack.toString())
                .add(defense.toString())
                .toString();
    }
}
