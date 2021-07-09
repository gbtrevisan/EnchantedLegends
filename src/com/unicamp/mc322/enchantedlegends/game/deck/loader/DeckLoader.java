package com.unicamp.mc322.enchantedlegends.game.deck.loader;

import com.unicamp.mc322.enchantedlegends.game.deck.concrete.Deck;
import com.unicamp.mc322.enchantedlegends.game.filemanager.FileLoader;
import com.unicamp.mc322.enchantedlegends.game.filemanager.converter.JsonConverterToDeck;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class DeckLoader {
    private static DeckLoader instance;

    private DeckLoader() {
    }

    public static DeckLoader getInstance() {
        if (instance == null) {
            instance = new DeckLoader();
        }

        return instance;
    }

    public Deck createDeck(String path) throws ParseException, IOException {
        JsonConverterToDeck jsonConverterToDeck = new JsonConverterToDeck();
        FileLoader fileLoader = FileLoader.getInstance();

        return jsonConverterToDeck.getDeck(fileLoader.loadFile(path));
    }
}