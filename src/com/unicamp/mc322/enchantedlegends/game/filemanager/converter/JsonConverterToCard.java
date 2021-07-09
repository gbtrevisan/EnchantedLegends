package com.unicamp.mc322.enchantedlegends.game.filemanager.converter;

import com.unicamp.mc322.enchantedlegends.game.card.Card;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonConverterToCard extends JsonConverter {
    public JsonConverterToCard() {
    }

    @Override
    protected Object getJSONObject(FileReader json) throws ParseException, IOException {
        Map<String, Card> gameCards = new HashMap<>();
        JSONObject jsonFile = (JSONObject) this.parser.parse(json);

        JSONArray cards = (JSONArray) jsonFile.get("cards");

        for (Object card : cards) {
            JSONObject gameCard = (JSONObject) card;

            String cardName = (String) gameCard.get("name");
            String cardType = (String) gameCard.get("type");
            JsonConverter cardCreator = getJsonConverter(cardType);

            gameCards.put(cardName, (Card) cardCreator.getJSONObject(json));
        }

        return gameCards;
    }

    public Map<String, Card> createCard(FileReader json) throws ParseException, IOException {
        return (Map<String, Card>) getJSONObject(json);
    }

    protected int getClassAtribute(JSONObject cardObject, String atributeName) {
        return (int) cardObject.get(atributeName);
    }

    protected List<String> loadEffect(JSONArray effectJSON) {
        List<String> cardEffects = new ArrayList<>();

        for (Object effect : effectJSON) {
            JSONObject cardEffect = (JSONObject) effect;

            cardEffects.add(createEffect(cardEffect));
        }

        return cardEffects;
    }

    private JsonConverter getJsonConverter(String cardType) {
        JsonConverter cardCreator = null;

        if (cardType.equalsIgnoreCase("FOLLOWER")) {
            cardCreator = new JsonConverterToFollower();
        } else if (cardType.equalsIgnoreCase("CHAMPION")) {
            cardCreator = new JsonConverterToChampion();
        } else if (cardType.equalsIgnoreCase("SPELL")) {
            cardCreator = new JsonConverterToSpell();
        }

        return cardCreator;
    }

    private String createEffect(JSONObject effect) {
        String effecType = (String) effect.get("type");

        if (effecType.equalsIgnoreCase("BOOST_ALL_UNITS")) {
            return "BOOST_ALL_UNITS";
        } else if (effecType.equalsIgnoreCase("BOOST_UNIT")) {
            int healthPoints = getClassAtribute(effect, "health");
            int damagePoints = getClassAtribute(effect, "damage");

            return "BOOST_UNIT Saude: " + healthPoints + " | Dano: " + damagePoints;
        } else if (effecType.equalsIgnoreCase("RECIEVE_CARD_ON_KILL")) {
            return "RECIEVE_CARD_ON_KILL";
        } else if (effecType.equalsIgnoreCase("RECIEVE_RANDOM_CARD_ON_DIE")) {
            return "RECIEVE_RANDOM_CARD_ON_DIE";
        } else if (effecType.equalsIgnoreCase("ATTACK_ALL_DEFENDER")) {
            return "ATTACK_ALL_DEFENDER";
        } else if (effecType.equalsIgnoreCase("FULL_HEALTH")) {
            return "FULL_HEALTH";
        } else {
            return " -- CANSEI --";
        }
    }
}
