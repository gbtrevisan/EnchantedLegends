package com.unicamp.mc322.enchantedlegends.game.card.effect;

import com.unicamp.mc322.enchantedlegends.game.card.effect.exception.EffectException;
import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.card.event.EventListener;

public abstract class Effect implements EventListener {

    private final CardEvent event;

    public Effect(CardEvent event) {
        if (event == null) {
            throw new EffectException("Effect event should not be null");
        }

        this.event = event;
    }

    @Override
    public void update(CardEvent event) {
        if (matchEvent(event)) {
            apply();
        }
    }

    protected abstract void apply();

    public boolean matchEvent(CardEvent event) {
        return this.event == event;
    }

}
