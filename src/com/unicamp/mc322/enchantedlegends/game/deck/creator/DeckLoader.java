package com.unicamp.mc322.enchantedlegends.game.deck.creator;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.deck.concrete.Deck;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DeckLoader extends DeckCreator {
    private final static String RELATIVE_PATH_JSON = "src/com/unicamp/mc322/enchantedlegends/game/deck/decksdata/";

    public DeckLoader() {
    }

    @Override
    public Deck createDeck(String deckName) {
        List<Card> deckCards = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonFIile = (JSONObject) parser.parse(new FileReader(RELATIVE_PATH_JSON + deckName + ".json"));

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

        return new Deck(deckCards);
    }
}