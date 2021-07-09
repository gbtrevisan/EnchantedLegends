package com.unicamp.mc322.enchantedlegends.game.deck.concrete;

import com.unicamp.mc322.enchantedlegends.game.GameObject;
import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.deck.DeckException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Deck implements GameObject {
    private final List<Card> deckCards;
    private final static int MAX_CARDS = 40;

    public Deck(String name, List<Card> deckCards) {
        Objects.requireNonNull(name);

        if (deckCards == null || deckCards.isEmpty() || deckCards.size() > MAX_CARDS) {
            throw new DeckException("Your deck cannot be empty or over " + MAX_CARDS + " cards");
        }

        this.deckCards = deckCards;
    }
}
