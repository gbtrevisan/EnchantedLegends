package com.unicamp.mc322.enchantedlegends.game.serialization.cards;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.serialization.JsonLoader;
import org.json.simple.JSONObject;

public class CardLoader extends JsonLoader {

    @Override
    public Card parseToObject(Object json) {
        JSONObject cardGot = (JSONObject) json;
        String cardType = (String) cardGot.get("type");
        Card card = null;
        try {
            Class<?> cardClass = Class.forName(cardType);
            card = (Card) buildClass(cardClass, cardGot);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return card;
    }
}
