package com.unicamp.mc322.enchantedlegends.game.cards.creator;

import com.unicamp.mc322.enchantedlegends.game.cards.GameCards;
import com.unicamp.mc322.enchantedlegends.game.filemanager.json.converter.JsonConverterToCard;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CardLoader {
    private static CardLoader instance;

    private CardLoader() {
    }

    public static CardLoader getInstance() {
        if (instance == null) {
            instance = new CardLoader();
        }

        return instance;
    }

    public void createCard(String json) throws ParseException, IOException {
        JsonConverterToCard jsonConverterCard = new JsonConverterToCard();
        GameCards gameCards = GameCards.getInstance();

        gameCards.addGameCard(jsonConverterCard.createCard(json));
    }
}
