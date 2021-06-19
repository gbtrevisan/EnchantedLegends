package com.unicamp.mc322.enchantedlegends.game.card.trait;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

import java.util.List;

public class ElusiveTrait implements Trait {

    @Override
    public void applyIfApplicable(Follower self, Follower enemy) throws ElusiveDefenseException {
        List<Trait> defenderTraits = enemy.getTraits();
        if (defenderTraits.stream().noneMatch(trait -> trait instanceof ElusiveTrait)) {
            throw new ElusiveDefenseException("An elusive card can only be defended by another elusive card!");
        }
    }
}
