package com.unicamp.mc322.enchantedlegends.game.serialization.deck;

import java.util.*;

public class Decks {
    private final static String DECKS_FILE = "decks.json";
    private static Decks instance;
    private final Map<String, Deck> decks;

    private Decks() {
        this.decks = new HashMap<>();
    }

    public static Decks getInstance() {
        if (instance == null) {
            instance = new Decks();
        }

        return instance;
    }

    public void addDeck(Deck deck) {
        this.decks.put(deck.getName(), deck);
    }

    public Deck getByName(String name) {
        return decks.get(name);
    }
}
