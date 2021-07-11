package com.unicamp.mc322.enchantedlegends.game.card.unit.trait.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.card.unit.trait.Trait;

public class DoubleAttackTrait extends Trait {

    private Follower follower;

    public DoubleAttackTrait(Follower follower) {
        super(CardEvent.ACTIVATE);
    }

    @Override
    protected void apply() {
        follower.increaseDamage(follower.getDamage() * 2);
    }
}
