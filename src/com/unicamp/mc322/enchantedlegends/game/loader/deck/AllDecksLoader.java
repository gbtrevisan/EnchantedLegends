package com.unicamp.mc322.enchantedlegends.game.loader.deck;

import com.unicamp.mc322.enchantedlegends.game.loader.cards.AllCardsLoader;
import com.unicamp.mc322.enchantedlegends.game.file.FileLoader;
import com.unicamp.mc322.enchantedlegends.game.loader.JsonLoader;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllDecksLoader extends JsonLoader {
    private static AllDecksLoader instance;

    public static void main(String[] args) throws IOException, ParseException {
        AllCardsLoader.getInstance().createAllCards();
        AllDecksLoader.getInstance().createAllDecks();
    }

    public static AllDecksLoader getInstance() {
        if (instance == null) {
            instance = new AllDecksLoader();
        }

        return instance;
    }

    public void createAllDecks() throws ParseException, IOException {
        List<Deck> decks = parseToObject(FileLoader.getInstance().loadFileAsString(getClass(), "decks.json"));
        decks.forEach(deck -> Decks.getInstance().addDeck(deck));
        decks.forEach(System.out::println);
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
