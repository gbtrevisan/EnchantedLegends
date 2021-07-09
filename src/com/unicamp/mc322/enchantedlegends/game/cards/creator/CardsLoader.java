package com.unicamp.mc322.enchantedlegends.game.cards.creator;

import com.unicamp.mc322.enchantedlegends.game.filemanager.json.JsonLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CardsLoader {
    private static CardsLoader instance;
    private final static String ALL_CARDS_JSON = "all_cards.json";

    private CardsLoader() {
    }

    public static CardsLoader getInstance() {
        if (instance == null) {
            instance = new CardsLoader();
        }

        return instance;
    }

    public void createGameCards() throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        JsonLoader jsonLoader = JsonLoader.getInstance();

        JSONObject jsonFile = (JSONObject) parser.parse(jsonLoader.loadJson(ALL_CARDS_JSON));

        JSONArray cards = (JSONArray) jsonFile.get("cards");

        for (Object card : cards) {
            JSONObject cardJson = (JSONObject) card;

            CardLoader.getInstance().createCard(cardJson.toJSONString());
        }
    }
}
