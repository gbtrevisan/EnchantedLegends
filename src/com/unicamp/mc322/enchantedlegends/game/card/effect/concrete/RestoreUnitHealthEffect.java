package com.unicamp.mc322.enchantedlegends.game.card.effect.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.effect.exception.EffectException;
import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;

public class RestoreUnitHealthEffect extends Effect {

    private int amount;

    public RestoreUnitHealthEffect(int health) {
        super(CardEvent.ACTIVATE);

        if (amount <= 0) {
            throw new EffectException("RestoreUnitHealthEffect health should be a positive number");
        }

        amount = health;
    }

    @Override
    protected void apply() {
        GameState.getInstance().getSelf().restoreUnitHealth(amount);
    }

}
