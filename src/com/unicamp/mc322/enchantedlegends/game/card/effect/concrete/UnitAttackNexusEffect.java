package com.unicamp.mc322.enchantedlegends.game.card.effect.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;

public class UnitAttackNexusEffect extends Effect {

    public UnitAttackNexusEffect() {
        super(CardEvent.ACTIVATE);
    }

    @Override
    protected void apply() {
        GameState.getInstance().getSelf().attackEnemyNexus(GameState.getInstance().getEnemy());
    }

}
