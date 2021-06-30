package com.unicamp.mc322.enchantedlegends.game.card.trait;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

public class ElusiveTrait implements Trait {

    private static ElusiveTrait instance;

    public static ElusiveTrait getInstance() {
        if (instance == null) {
            instance = new ElusiveTrait();
        }

        return instance;
    }

    @Override
    public void applyIfApplicable(Follower self, Follower enemy) throws ElusiveDefenseException {
        if (enemy.dontHasTrait(this)) {
            throw new ElusiveDefenseException("An elusive card can only be defended by another elusive card!");
        }
    }
}
