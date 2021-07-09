package com.unicamp.mc322.enchantedlegends.game.card.trait;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

import java.util.StringJoiner;

public class ElusiveTrait implements Trait {

    @Override
    public void applyIfApplicable(Follower self, Follower enemy) throws ElusiveDefenseException {
        if (enemy.dontHasTrait(this)) {
            throw new ElusiveDefenseException("An elusive card can only be defended by another elusive card!");
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ElusiveTrait.class.getSimpleName() + "[", "]")
                .toString();
    }
}
