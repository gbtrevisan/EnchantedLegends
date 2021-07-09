package com.unicamp.mc322.enchantedlegends.game.deck.data;

import com.unicamp.mc322.enchantedlegends.game.deck.DeckException;
import com.unicamp.mc322.enchantedlegends.game.deck.concrete.Deck;
import com.unicamp.mc322.enchantedlegends.game.deck.loader.DeckLoader;
import com.unicamp.mc322.enchantedlegends.game.filemanager.json.FileLoader;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Decks {
    private static Decks instance;
    private final DeckLoader deckLoader;
    private final List<Deck>  decks;
    private final static String RELATIVE_PATH_JSON = "src/com/unicamp/mc322/enchantedlegends/game/deck/decksdata/";
    private final static String DECK_FILE = "_deck.json";

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

    public List<Deck> createAllDecks() throws ParseException, IOException {
        FileLoader fileLoader = FileLoader.getInstance();
        loadAllDecks(fileLoader.loadDirectory(RELATIVE_PATH_JSON));

        return this.decks;
    }

    private void loadAllDecks(File folder) throws ParseException, IOException {
        List<File> files = Arrays.asList(folder.listFiles());

        if (files == null) {
            throw new DeckException("Unable to load desired deck");
        }

        for (File file : files) {
            if (file.isDirectory()) {
                loadAllDecks(file);
            } else {
                if (file.getName().endsWith(DECK_FILE)) {
                    this.decks.add(this.deckLoader.createDeck(file.getName()));
                }
            }
        }
    }
}
