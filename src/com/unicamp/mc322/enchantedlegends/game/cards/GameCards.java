package com.unicamp.mc322.enchantedlegends.game.cards;

import com.unicamp.mc322.enchantedlegends.game.card.Card;

import java.util.HashMap;
import java.util.Map;

public class GameCards {
    private static GameCards instance;
    private Map<String, Card> gameCards;

    private GameCards() {
        this.gameCards = new HashMap<>();
    }

    public static GameCards getInstance() {
        if (instance == null) {
            instance = new GameCards();
        }

        return instance;
    }

    public void addGameCard(Map<String, Card> gameCards) {
        this.gameCards = gameCards;
    }

    public Card getCard(String cardName) {
        if (!this.gameCards.containsKey(cardName)) {
            throw new GameCardException("Could not find any card with this name (name = " + cardName + ")");
        }

        return this.gameCards.get(cardName);
    }
}
