package com.unicamp.mc322.enchantedlegends.game.deck.loader;

import com.unicamp.mc322.enchantedlegends.game.deck.concrete.Deck;
import com.unicamp.mc322.enchantedlegends.game.filemanager.json.JsonLoader;
import com.unicamp.mc322.enchantedlegends.game.filemanager.json.converter.JsonConverterToDeck;
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

    public Deck createDeck(String fileName) throws ParseException, IOException {
        JsonConverterToDeck jsonConverterToDeck = new JsonConverterToDeck();
        JsonLoader jsonLoader = JsonLoader.getInstance();

        return jsonConverterToDeck.getDeck(jsonLoader.loadJson(fileName));
    }
}