package com.unicamp.mc322.enchantedlegends.game.serialization.deck;

import com.unicamp.mc322.enchantedlegends.game.GameObject;
import com.unicamp.mc322.enchantedlegends.game.GameObjectVisitor;
import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.serialization.cards.GameCards;

import java.util.*;

public class Deck implements GameObject {
    private final String name;
    private final List<Card> cards;
    private final static int MAX_CARDS = 40;

    public Deck(String name, List<String> cardNames) {
        Objects.requireNonNull(name);
        this.name = name;
        this.cards = new ArrayList<>();

        cardNames.forEach(cardName -> cards.add(GameCards.getInstance().getByName(cardName)));
    }

    public void addCard(Card card) {
        if (cards.size() > MAX_CARDS) {
            throw new DeckException("Your deck cannot be over " + MAX_CARDS + " cards");
        }

        this.cards.add(card);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Deck.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("cards=" + cards)
                .toString();
    }

    @Override
    public void accept(GameObjectVisitor visitor) {

    }
}
