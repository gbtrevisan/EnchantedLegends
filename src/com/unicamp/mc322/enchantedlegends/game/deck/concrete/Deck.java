package com.unicamp.mc322.enchantedlegends.game.deck.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.deck.DeckException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Deck {
    private final Map<Integer, Card> deckCards;
    private final static int MAX_CARDS = 40;

    public Deck(String name, Map<Integer, Card> deckCards) {
        Objects.requireNonNull(name);

        if (deckCards.isEmpty() || deckCards.size() > MAX_CARDS) {
            throw new DeckException("Your deck cannot be empty or over " + MAX_CARDS + " cards");
        }

        this.deckCards = deckCards;
    }

    public Card getCard(int cardId) {
        if (!this.deckCards.containsKey(cardId)) {
            throw new DeckException("Could not find the card");
        }

        return this.deckCards.get(cardId);
    }

    public List<Card> getAllCards() {
        List<Card> allCards = new ArrayList<>();
        allCards.addAll(this.deckCards.values());

        return allCards;
    }
}
