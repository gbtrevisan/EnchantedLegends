package com.unicamp.mc322.enchantedlegends.game.json.parser;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.cards.GameCards;
import com.unicamp.mc322.enchantedlegends.game.deck.concrete.Deck;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class DeckJsonParser extends JsonParser {

    @Override
    public Deck parseToObject(String json) throws ParseException {
        JSONObject deckJson = (JSONObject) parser.parse(json);
        String name = (String) deckJson.get("name");
        JSONArray cardsJson = (JSONArray) deckJson.get("cards");

        List<Card> cards = new ArrayList<>();
        for (Object o : cardsJson) {
            cards.add(GameCards.getInstance().getCard((String) o));
        }

        return new Deck(name, cards);
    }
}
