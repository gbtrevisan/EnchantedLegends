package com.unicamp.mc322.enchantedlegends.game.serialization.deck;

import com.unicamp.mc322.enchantedlegends.game.file.FileLoader;
import com.unicamp.mc322.enchantedlegends.game.serialization.JsonLoader;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class AllDecksLoader extends JsonLoader {
    public static final String DECKS_JSON_FILENAME = "decks.json";
    private static AllDecksLoader instance;

    public static AllDecksLoader getInstance() {
        if (instance == null) {
            instance = new AllDecksLoader();
        }

        return instance;
    }

    public void createAllDecksByDefault() {
        List<Deck> decks = Arrays.asList(
                new Deck("Demacia", Arrays.asList("Garen", "Vanguarda", "Duelista")),
                new Deck("Noxus", Collections.emptyList()),
                new Deck("Targon", Collections.emptyList())
        );

        decks.forEach(deck -> Decks.getInstance().addDeck(deck));
    }

    public void createAllDecksFromJson() {
        try {
            List<Deck> decks = parseToObject(FileLoader.getInstance().loadFileAsString(getClass(), DECKS_JSON_FILENAME));
            decks.forEach(deck -> Decks.getInstance().addDeck(deck));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Deck> parseToObject(Object json) throws ParseException, IOException {
        JSONArray decks = (JSONArray) ((Map<String, Object>) this.parser.parse((String) json)).get("decks");
        JsonLoader jsonParser = new DeckLoader();
        List<Deck> deckList = new ArrayList<>();

        for (Object deckJson : decks) {
            Deck deck = (Deck) jsonParser.parseToObject(deckJson);
            deckList.add(deck);
        }

        return deckList;
    }
}
