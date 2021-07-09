package com.unicamp.mc322.enchantedlegends.game.filemanager.converter;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.cards.GameCards;
import com.unicamp.mc322.enchantedlegends.game.deck.concrete.Deck;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonConverterToDeck extends JsonConverter {
    public JsonConverterToDeck() {
        super();
    }

    @Override
    protected Deck getJSONObject(FileReader json) throws ParseException, IOException  {
        String deckName = "";
        Map<String, Card> deckCards = new HashMap<>();

        JSONObject jsonFIile = (JSONObject) this.parser.parse(json);

        deckName = (String) jsonFIile.get("name");
        JSONArray cards = (JSONArray) jsonFIile.get("cards");

        for (Object card : cards) {
            String cardName = (String) card;
            deckCards.put(cardName, GameCards.getInstance().getCard(cardName));
        }

        return new Deck(deckName, deckCards);
    }

    public Deck getDeck(FileReader json) throws ParseException, IOException {
        return getJSONObject(json);
    }
}
