package com.unicamp.mc322.enchantedlegends.game.card.effect.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.effect.exception.EffectException;
import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;

public class AttackNexusEffect extends Effect {

    private final int amount;

    public AttackNexusEffect(int damage) {
        super(CardEvent.ACTIVATE);

        if (damage <= 0) {
            throw new EffectException("AttackNexusEffect damage must be a positive number");
        }

        amount = damage;
    }

    @Override
    protected void apply() {
        GameState.getInstance().getSelf().attackEnemyNexus(amount);
    }

}
