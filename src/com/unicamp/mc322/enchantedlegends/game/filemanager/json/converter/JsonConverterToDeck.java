package com.unicamp.mc322.enchantedlegends.game.filemanager.json.converter;

import com.unicamp.mc322.enchantedlegends.game.cards.GameCards;
import com.unicamp.mc322.enchantedlegends.game.deck.concrete.Deck;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class JsonConverterToDeck extends JsonConverter {
    public JsonConverterToDeck() {
        super();
    }

    @Override
    protected Deck getJSONObject(String json) throws ParseException {
        JSONObject jsonFIile = (JSONObject) this.parser.parse(json);

        String deckName = getClassStringAttribute(jsonFIile, "name");
        JSONArray cards = (JSONArray) jsonFIile.get("cards");

        Deck deck = new Deck(deckName);

        for (Object card : cards) {
            String cardName = (String) card;
            deck.addCard(cardName, GameCards.getInstance().getByName(cardName));
        }

        return deck;
    }

    public Deck getDeck(String json) throws ParseException {
        return getJSONObject(json);
    }
}
