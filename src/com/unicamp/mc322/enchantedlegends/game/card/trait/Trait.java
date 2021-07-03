package com.unicamp.mc322.enchantedlegends.game.card.trait;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

public interface Trait {

    void applyIfApplicable(Follower self, Follower enemy) throws TraitException;
}
