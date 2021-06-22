package com.unicamp.mc322.enchantedlegends.game.card.trait;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

import java.util.List;

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
        List<Trait> defenderTraits = enemy.getTraits();
        if (defenderTraits.stream().noneMatch(this::equals)) {
            throw new ElusiveDefenseException("An elusive card can only be defended by another elusive card!");
        }
    }
}
