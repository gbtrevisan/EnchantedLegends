package com.unicamp.mc322.enchantedlegends.game.cards.creator;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.cards.GameCards;
import com.unicamp.mc322.enchantedlegends.game.json.FileLoader;
import com.unicamp.mc322.enchantedlegends.game.json.parser.CardJsonParser;
import com.unicamp.mc322.enchantedlegends.game.json.parser.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllCardsLoader extends JsonParser {
    private static AllCardsLoader instance;
    private final static String PATH_JSON_CARDS = "all_cards.json";

    private AllCardsLoader() {
    }

    public static AllCardsLoader getInstance() {
        if (instance == null) {
            instance = new AllCardsLoader();
        }

        return instance;
    }

    public void createAllCards() throws ParseException, IOException {
        List<Card> cardList = parseToObject(FileLoader.getInstance().loadFileAsString(getClass(), PATH_JSON_CARDS));
        cardList.forEach(card -> GameCards.getInstance().addCard(card));
    }

    @Override
    public List<Card> parseToObject(String json) throws ParseException, IOException {
        JSONArray cards = (JSONArray) ((Map<String, Object>) this.parser.parse(json)).get("cards");
        JsonParser jsonParser = new CardJsonParser();
        List<Card> cardList = new ArrayList<>();

        for (Object cardJson: cards) {
            Card card = (Card) jsonParser.parseToObject((String) cardJson);
            cardList.add(card);
        }

        return cardList;
    }

    public static void main(String[] args) throws IOException, ParseException {
        AllCardsLoader.getInstance().createAllCards();
    }
}
