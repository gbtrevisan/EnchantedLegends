package com.unicamp.mc322.enchantedlegends.game.deck.decksdata;

import com.unicamp.mc322.enchantedlegends.game.deck.concrete.Deck;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Decks {
    //private final DeckLoader deckLoader;
    private final List<Deck>  decks;
    private final static String RELATIVE_PATH_JSON = "src/com/unicamp/mc322/enchantedlegends/game/deck/decksdata/";

    public Decks() {
        this.decks = new ArrayList<>();
        //this.deckLoader = DeckLoader
    }

    public void createAllDecks() {
        File folder = new File(RELATIVE_PATH_JSON);
        findAllDecks(folder);
    }

    private void findAllDecks(File folder) {
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (!file.isDirectory()) {
                if (file.getName().endsWith(".json")) {
                    System.out.println(file.getName());
                }
            } else {
                findAllDecks(file);
            }
        }
    }
}
