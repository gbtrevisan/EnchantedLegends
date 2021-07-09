package com.unicamp.mc322.enchantedlegends.game.deck.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.deck.DeckException;

import java.util.*;

public class Deck {
    private final Map<String, Card> deckCards;
    private final static int MAX_CARDS = 40;

    public Deck(String name) {
        Objects.requireNonNull(name);
        this.deckCards = new HashMap<>();
    }

    public void addCard(String name, Card card) {
        if (deckCards.size() > MAX_CARDS) {
            throw new DeckException("Your deck cannot be over " + MAX_CARDS + " cards");
        }

        this.deckCards.put(name, card);
    }

    public Card getCard(String cardName) {
        if (!this.deckCards.containsKey(cardName)) {
            throw new DeckException("Could not find the card " + cardName);
        }

        return this.deckCards.get(cardName);
    }

    public List<Card> getAllCards() {
        List<Card> allCards = new ArrayList<>();
        allCards.addAll(this.deckCards.values());

        return allCards;
    }
}
