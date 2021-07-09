package com.unicamp.mc322.enchantedlegends.game.deck.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.deck.DeckException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Deck {
    private final Map<String, Card> deckCards;
    private final static int MAX_CARDS = 40;

    public Deck(String name, Map<String, Card> deckCards) {
        Objects.requireNonNull(name);

        if (deckCards == null || deckCards.isEmpty() || deckCards.size() > MAX_CARDS) {
            throw new DeckException("Your deck cannot be empty or over " + MAX_CARDS + " cards");
        }

        this.deckCards = deckCards;
    }

    public Card getCard(String cardName) {
        if (!this.deckCards.containsKey(cardName)) {
            throw new DeckException("Could not find the card");
        }

        return this.deckCards.get(cardName);
    }

    public List<Card> getAllCards() {
        List<Card> allCards = new ArrayList<>();
        allCards.addAll(this.deckCards.values());

        return allCards;
    }
}
