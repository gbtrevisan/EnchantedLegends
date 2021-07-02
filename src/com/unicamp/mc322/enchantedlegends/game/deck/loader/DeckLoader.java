package com.unicamp.mc322.enchantedlegends.game.deck.loader;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.deck.concrete.Deck;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

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

    public Deck createDeck(String path) {
        String deckName = "";
        Map<Integer, Card> deckCards = new HashMap<>();
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonFIile = (JSONObject) parser.parse(new FileReader(path));

            /*JSONArray cards = (JSONArray) jsonFIile.get("cards");

            for (Object card : cards) {
                JSONObject cardObj = (JSONObject) card;
                createCard(cardObj);

                System.out.println("\n");
            }*/
        }
        catch (Exception e) {
            System.out.println("Could not find your deck: " + e.getMessage());
        }

        return new Deck(deckName, deckCards);
    }
}