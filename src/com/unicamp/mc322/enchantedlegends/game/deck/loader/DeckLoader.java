package com.unicamp.mc322.enchantedlegends.game.deck.loader;

import com.unicamp.mc322.enchantedlegends.game.deck.concrete.Deck;
import com.unicamp.mc322.enchantedlegends.game.json.FileLoader;
import com.unicamp.mc322.enchantedlegends.game.json.parser.DeckJsonParser;
import com.unicamp.mc322.enchantedlegends.game.json.parser.JsonParser;
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
        JsonParser deckJsonParser = new DeckJsonParser();
        return (Deck) deckJsonParser.parseToObject(FileLoader.getInstance().loadFileAsString(getClass(), path));
    }
}