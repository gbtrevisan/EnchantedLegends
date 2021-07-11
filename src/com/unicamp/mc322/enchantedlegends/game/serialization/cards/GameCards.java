package com.unicamp.mc322.enchantedlegends.game.serialization.cards;

import com.unicamp.mc322.enchantedlegends.game.card.Card;

import java.util.HashMap;
import java.util.Map;

public class GameCards {
    private static GameCards instance;
    private final Map<String, Card> gameCards;

    private GameCards() {
        this.gameCards = new HashMap<>();
    }

    public static GameCards getInstance() {
        if (instance == null) {
            instance = new GameCards();
        }

        return instance;
    }

    public void addCard(Card card) {
        this.gameCards.put(card.getName(), card);
    }

    public Card getByName(String cardName) {
        if (!this.gameCards.containsKey(cardName)) {
            throw new GameCardsException("Could not find any card with this name (name = " + cardName + ")");
        }

        return this.gameCards.get(cardName);
    }
}
