package com.unicamp.mc322.enchantedlegends.game.deck.concrete;

import com.unicamp.mc322.enchantedlegends.game.GameObject;
import com.unicamp.mc322.enchantedlegends.game.GameObjectVisitor;
import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.deck.DeckException;

import java.util.*;

public class Deck implements GameObject {
    private final List<Card> deckCards;
    private final static int MAX_CARDS = 40;

    public Deck(String name, List<Card> deckCards) {
        Objects.requireNonNull(name);
        this.deckCards = new ArrayList<>();
    }

    public void addCard(Card card) {
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
        return new ArrayList<>(this.deckCards.values());

        this.deckCards.add(card);
    }

    @Override
    public void accept(GameObjectVisitor visitor) {
      
    }
}
