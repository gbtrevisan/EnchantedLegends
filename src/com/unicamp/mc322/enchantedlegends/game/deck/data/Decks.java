package com.unicamp.mc322.enchantedlegends.game.deck.data;

import com.unicamp.mc322.enchantedlegends.game.deck.DeckException;
import com.unicamp.mc322.enchantedlegends.game.deck.concrete.Deck;
import com.unicamp.mc322.enchantedlegends.game.deck.loader.DeckLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Decks {
    private static Decks instance;
    private final DeckLoader deckLoader;
    private final List<Deck>  decks;
    private final static String RELATIVE_PATH_JSON = "src/com/unicamp/mc322/enchantedlegends/game/deck/decksdata/";

    private Decks() {
        this.decks = new ArrayList<>();
        this.deckLoader = DeckLoader.getInstance();
    }

    public static Decks getInstance() {
        if (instance == null) {
            instance = new Decks();
        }

        return instance;
    }

    public List<Deck> createAllDecks() {
        File folder = new File(RELATIVE_PATH_JSON);
        loadAllDecks(folder);

        if (this.decks.isEmpty()) {
            throw new DeckException("Unable to load any deck");
        }

        return this.decks;
    }

    private void loadAllDecks(File folder) {
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (!file.isDirectory()) {
                if (file.getName().endsWith(".json")) {
                    this.decks.add(this.deckLoader.createDeck(file.getPath()));
                }
            } else {
                loadAllDecks(file);
            }
        }
    }
}
