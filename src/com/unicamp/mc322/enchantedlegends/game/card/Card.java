package com.unicamp.mc322.enchantedlegends.game.card;

import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.card.event.EventManager;
import com.unicamp.mc322.enchantedlegends.game.card.exception.CardCreationException;
import com.unicamp.mc322.enchantedlegends.game.card.mana.Mana;
import com.unicamp.mc322.enchantedlegends.game.card.mana.exception.InsufficientManaException;

import java.util.Objects;
import java.util.StringJoiner;

public abstract class Card {
    protected final int cost;
    private final String name;
    private final EventManager eventManager;

    public Card(String name, int cost, Effect... effects) {
        Objects.requireNonNull(name, "Card name must not be null");

        this.name = name;

        if (cost < 0) {
            throw new CardCreationException("Card cost must not be negative");
        }

        this.cost = cost;
        this.eventManager = new EventManager();

        for (Effect effect : effects) {
            eventManager.subscribe(effect);
        }
    }

    protected void updateEventManager(CardEvent event) {
        eventManager.update(event);
    }

    public void activate(Mana mana) {
        try {
            mana.use(cost);
            updateEventManager(CardEvent.ACTIVATE);
        } catch (InsufficientManaException e) {
            throw new CardException("Not enough mana to activate this card!", e);
        }
    }

    public void addEffect(Effect effect) {
        eventManager.subscribe(effect);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Card.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("cost=" + cost)
                .toString();
    }
}
