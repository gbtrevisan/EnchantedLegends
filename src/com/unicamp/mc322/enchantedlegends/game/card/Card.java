package com.unicamp.mc322.enchantedlegends.game.card;

import com.unicamp.mc322.enchantedlegends.game.GameObject;
import com.unicamp.mc322.enchantedlegends.game.GameObjectVisitor;
import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.card.event.EventListener;
import com.unicamp.mc322.enchantedlegends.game.card.event.EventManager;
import com.unicamp.mc322.enchantedlegends.game.card.mana.Mana;
import com.unicamp.mc322.enchantedlegends.game.card.mana.exception.InsufficientManaException;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public abstract class Card implements GameObject {

    protected String name;
    protected int cost;
    private EventManager eventManager;

    public Card() {
    }

    public Card(String name, int cost) {
        this(name, cost, null);
    }

    public Card(String name, int cost, List<Effect> effects) {
        Objects.requireNonNull(name, "Card name must not be null");

        this.name = name;

        if (cost < 0) {
            throw new CardCreationException("Card cost must not be negative");
        }

        this.cost = cost;
        this.eventManager = new EventManager();

        if (effects == null) {
            return;
        }

        for (Effect effect : effects) {
            eventManager.subscribe(effect);
        }
    }

    public String getName() {
        return name;
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

    public void addEventListener(EventListener eventListener) {
        eventManager.subscribe(eventListener);
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Card.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("cost=" + cost)
                .toString();
    }

    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {

    }
}
