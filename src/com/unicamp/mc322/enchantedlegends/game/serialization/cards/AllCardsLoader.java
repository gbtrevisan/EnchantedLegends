package com.unicamp.mc322.enchantedlegends.game.serialization.cards;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.file.FileLoader;
import com.unicamp.mc322.enchantedlegends.game.serialization.JsonLoader;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllCardsLoader extends JsonLoader {
    private static AllCardsLoader instance;
    private final static String JSON_CARDS_FILENAME = "all_cards.json";

    public static AllCardsLoader getInstance() {
        if (instance == null) {
            instance = new AllCardsLoader();
        }

        return instance;
    }

    public void createAllCards() throws ParseException, IOException {
        List<Card> cardList = parseToObject(FileLoader.getInstance().loadFileAsString(getClass(), JSON_CARDS_FILENAME));
        cardList.forEach(card -> GameCards.getInstance().addCard(card));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Card> parseToObject(Object json) throws ParseException, IOException {
        JSONArray cards = (JSONArray) ((Map<String, Object>) this.parser.parse((String) json)).get("cards");
        JsonLoader jsonParser = new CardLoader();
        List<Card> cardList = new ArrayList<>();

        for (Object cardJson : cards) {
            Card card = (Card) jsonParser.parseToObject(cardJson);
            cardList.add(card);
        }

        return cardList;
    }
}
