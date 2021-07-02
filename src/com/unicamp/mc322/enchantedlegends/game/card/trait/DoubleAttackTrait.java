package com.unicamp.mc322.enchantedlegends.game.card.trait;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;
import com.unicamp.mc322.enchantedlegends.game.util.Pair;

public class DoubleAttackTrait implements Trait {

    @Override
    public void applyIfApplicable(Follower self, Follower enemy) {
        GameState.getInstance().addCombat(Pair.of(self, enemy));
    }
}
