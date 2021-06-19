package com.unicamp.mc322.enchantedlegends.game.card;

import com.unicamp.mc322.enchantedlegends.game.card.exception.CardCreationException;
import com.unicamp.mc322.enchantedlegends.game.card.mana.exception.InsufficientManaException;
import com.unicamp.mc322.enchantedlegends.game.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.event.Event;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public abstract class Card {

    protected final int cost;
    protected final List<Effect> effects;
    private final String name;

    public Card(String name, int cost, Effect... effects) {
        Objects.requireNonNull(name, "Card name must not be null");

        this.name = name;

        if (cost < 0) {
            throw new CardCreationException("Card cost must not be negative");
        }

        this.cost = cost;
        this.effects = Arrays.asList(effects);
    }

    protected void applyEffects(GameState gameState, Event event) {
        effects.stream().filter(effect -> effect.applicableOnEvent(event)).forEach(effect -> effect.apply(gameState));
    }

    public void activate(GameState gameState) {
        try {
            gameState.getSelf().getMana().use(cost);
            this.applyEffects(gameState, Event.ACTIVATION);
        } catch (InsufficientManaException e) {
            throw new CardException("Not enough mana to activate this card!", e);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Card.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("cost=" + cost)
                .add("effects=" + effects)
                .toString();
    }
}
