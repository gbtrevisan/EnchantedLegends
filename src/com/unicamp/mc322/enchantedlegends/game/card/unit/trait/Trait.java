package com.unicamp.mc322.enchantedlegends.game.card.unit.trait;

import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.card.event.EventListener;
import com.unicamp.mc322.enchantedlegends.game.card.unit.trait.exception.TraitException;

public abstract class Trait implements EventListener {

    private final CardEvent event;

    public Trait(CardEvent event) {
        if (event == null) {
            throw new TraitException("Trait event should not be null");
        }

        this.event = event;
    }

    @Override
    public void update(CardEvent event) {
        if (event == this.event) {
            apply();
        }
    }

    protected abstract void apply();

}
