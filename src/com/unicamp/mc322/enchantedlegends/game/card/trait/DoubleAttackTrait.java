package com.unicamp.mc322.enchantedlegends.game.card.trait;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;

public class DoubleAttackTrait implements Trait {

    @Override
    public void applyIfApplicable(Follower self, Follower enemy) {
        GameState.getInstance().addToCombatQueue(self, enemy);
    }
}
