package com.unicamp.mc322.enchantedlegends.game.serialization.deck;

import com.unicamp.mc322.enchantedlegends.game.file.FileLoader;
import com.unicamp.mc322.enchantedlegends.game.serialization.JsonWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class DeckSaver extends JsonWriter {

    public static final String DECKS_JSON_FILENAME = "decks.json";

    @Override
    public void saveObject(Object toSave) throws ParseException, IOException {
        JSONObject decks = (JSONObject) this.parser.parse(FileLoader.getInstance().loadFileAsString(getClass(), DECKS_JSON_FILENAME));
        // decks.put((Deck) toSave);
    }
}
