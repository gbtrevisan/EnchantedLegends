package com.unicamp.mc322.enchantedlegends.game.cards.creator;

import com.unicamp.mc322.enchantedlegends.game.cards.GameCards;
import com.unicamp.mc322.enchantedlegends.game.filemanager.FileLoader;
import com.unicamp.mc322.enchantedlegends.game.filemanager.converter.JsonConverterToCard;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CardLoader {
    private static CardLoader instance;
    private final static String PATH_JSON_CARDS = "all_cards.json";

    private CardLoader() {
    }

    public static CardLoader getInstance() {
        if (instance == null) {
            instance = new CardLoader();
        }

        return instance;
    }

    public void createCards() throws ParseException, IOException {
        JsonConverterToCard jsonConverterCard = new JsonConverterToCard();
        FileLoader fileLoader = FileLoader.getInstance();
        GameCards gameCards = GameCards.getInstance();

        gameCards.addGameCard(jsonConverterCard.createCard(fileLoader.loadFile(PATH_JSON_CARDS)));
    }
}
