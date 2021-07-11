package com.unicamp.mc322.enchantedlegends.game.card.unit.trait.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.card.unit.trait.Trait;

public class FuryTrait extends Trait {

    private int health;
    private int damage;
    private Follower follower;

    public FuryTrait(int health, int damage, Follower follower) {
        super(CardEvent.ENEMY_DESTROYED);
    }

    @Override
    protected void apply() {
        follower.increaseHealth(health);
        follower.increaseDamage(damage);
    }
}
