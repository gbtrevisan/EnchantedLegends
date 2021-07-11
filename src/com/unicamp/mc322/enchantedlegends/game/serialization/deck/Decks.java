package com.unicamp.mc322.enchantedlegends.game.serialization.deck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decks {
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

    public List<String> getAllDeckNames() {
        return new ArrayList<>(decks.keySet());
    }
}
