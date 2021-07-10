package com.unicamp.mc322.enchantedlegends.game.loader.deck;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.loader.cards.GameCards;
import com.unicamp.mc322.enchantedlegends.game.loader.JsonLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeckLoader extends JsonLoader {

    @Override
    public Deck parseToObject(Object json) {
        JSONObject deckJson = (JSONObject) json;
        String name = (String) deckJson.get("name");
        JSONArray cardsJson = (JSONArray) deckJson.get("cards");

        List<Card> cards = new ArrayList<>();
        for (Object o : cardsJson) {
            cards.add(GameCards.getInstance().getByName((String) o));
        }

        return new Deck(name, cards);
    }
}