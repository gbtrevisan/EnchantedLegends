package com.unicamp.mc322.enchantedlegends.card;

import com.unicamp.mc322.enchantedlegends.Event;
import com.unicamp.mc322.enchantedlegends.effect.Effect;
import com.unicamp.mc322.enchantedlegends.GameState;
import com.unicamp.mc322.enchantedlegends.Mana;

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
            throw new CardException("Card cost must not be negative");
        }

        this.cost = cost;
        this.effects = Arrays.asList(effects);
    }

    protected void applyEffects(GameState gameState, Event event) {
        effects.stream().filter(effect -> effect.applicableOnEvent(event)).forEach(effect -> effect.apply(gameState));
    }

    public abstract boolean activate(Mana mana, GameState gameState);

}
