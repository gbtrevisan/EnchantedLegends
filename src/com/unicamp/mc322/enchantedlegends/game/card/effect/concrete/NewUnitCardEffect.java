package com.unicamp.mc322.enchantedlegends.game.card.effect.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.effect.exception.EffectException;
import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;

public class NewUnitCardEffect extends Effect {

    private final String cardName;

    public NewUnitCardEffect(String cardName) {
        super(CardEvent.ENEMY_DESTROYED);

        if (cardName == null) {
            throw new EffectException("NewUnitCardEffect cardName must not be null");
        }

        this.cardName = cardName;
    }

    @Override
    protected void apply() {
        GameState.getInstance().getSelf().gainUnit(cardName);
    }

}
