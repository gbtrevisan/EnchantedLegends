package com.unicamp.mc322.enchantedlegends.game.card;

import com.unicamp.mc322.enchantedlegends.game.Event;
import com.unicamp.mc322.enchantedlegends.game.card.exception.CardCreationException;
import com.unicamp.mc322.enchantedlegends.game.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.GameState;
import com.unicamp.mc322.enchantedlegends.game.card.mana.exception.InsufficientManaException;
import com.unicamp.mc322.enchantedlegends.game.card.mana.Mana;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Card {
    private String name;
    protected final int cost;
    protected final List<Effect> effects;

    public Card(String name, int cost, Effect... effects) {
        Objects.requireNonNull(name, "Card name must not be null");

        if (cost < 0) {
            throw new CardCreationException("Card cost must not be negative");
        }

        this.cost = cost;
        this.effects = Arrays.asList(effects);
    }

    protected void applyEffects(Event event) {
        effects.stream().filter(effect -> effect.applicableOnEvent(event)).forEach(effect -> effect.apply());
    }

    public abstract void activate(Mana mana) throws InsufficientManaException;
}
