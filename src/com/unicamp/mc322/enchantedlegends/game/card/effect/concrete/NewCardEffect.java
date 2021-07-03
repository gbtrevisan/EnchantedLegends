package com.unicamp.mc322.enchantedlegends.game.card.effect.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;

public class NewCardEffect extends Effect {

    public NewCardEffect() {
        super(CardEvent.BE_DESTROYED);
    }

    @Override
    protected void apply() {
        GameState.getInstance().getSelf().gainRandomCard();
    }

}
