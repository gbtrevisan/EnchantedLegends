package com.unicamp.mc322.enchantedlegends.game.deck.cardscreator;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*
    CARD BUILDER
* */

public class CardsCreator {
    private static CardsCreator instance;
    private final static String PATH_JSON = "src/com/unicamp/mc322/enchantedlegends/game/deck/cardscreator/all_cards.json";
    private final List<Card> gameCards;

    private CardsCreator() {
        this.gameCards = new ArrayList<>();
    }

    public static CardsCreator getInstance() {
        if (instance == null) {
            instance = new CardsCreator();
        }

        return instance;
    }

    public void createCards() {
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonFile = (JSONObject) parser.parse(new FileReader(PATH_JSON));
            JSONArray cards = (JSONArray) jsonFile.get("cards");

            for (Object card : cards) {
                JSONObject cardObj = (JSONObject) card;
                instanceCard(cardObj);
            }
        }
        catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }

    private List<Integer> cardStatus(JSONObject card) {
        List<Integer> statusCard = new ArrayList<>();

        statusCard.add((int) ((long) card.get("health")));
        statusCard.add((int) ((long) card.get("damage")));

        return statusCard;
    }

    private String createEffect(JSONObject effect) {
        String effecType = (String) effect.get("type");

        if (effecType.equalsIgnoreCase("BOOST_ALL_UNITS")) {
            return "BOOST_ALL_UNITS";
        } else if (effecType.equalsIgnoreCase("BOOST_UNIT")) {
            int healthPoints = (int) ((long) effect.get("health"));
            int damagePoints = (int) ((long) effect.get("damage"));

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

    // List<Effect>
    private List<String> cardEffect(JSONArray effectJSON) {
        List<String> effect = new ArrayList<>();

        for (Object o : effectJSON) {
            JSONObject effectA = (JSONObject) o;

            effect.add(createEffect(effectA));
        }

        return effect;
    }

    private List<String> cardTrait(JSONObject traitJSON) {
        // list trait
        List<String> trait = new ArrayList<>();

        if (traitJSON != null) {
            String traitType = (String) traitJSON.get("type");

            if (traitType.equalsIgnoreCase("FURY")) {
                trait.add("FURY");
            } else if (traitType.equalsIgnoreCase("DOUBLE_ATTACK")) {
                trait.add("DOUBLE_ATTACK");
            } else {
                trait.add("ELUSIVE");
            }
        }

        return trait;
    }

    private List<String> cardUpgrade(JSONObject card) {
        // list<Upgrades>
        JSONArray powerUps = (JSONArray) card.get("powerups");
        List<String> upgrades = new ArrayList<>();

        for (Object o : powerUps) {
            JSONObject powerUp = (JSONObject) o;

            String type = (String) powerUp.get("type");

            if (type.equalsIgnoreCase("HEALTH")) {
                int healthPoints = (int) ((long) powerUp.get("health"));
                upgrades.add("HEALTH Pontos: " + healthPoints);
            } else if (type.equalsIgnoreCase("DAMAGE")) {
                int damagePoints = (int) ((long) powerUp.get("damage"));
                upgrades.add("DAMAGE Pontos: " + damagePoints);
            } else if (type.equalsIgnoreCase("EFFECT")) {
                JSONArray effect = (JSONArray) powerUp.get("effect");
                upgrades.addAll(cardEffect(effect));
            } else {
                JSONObject trait = (JSONObject) powerUp.get("trait");
                upgrades.addAll(cardTrait(trait));
            }
        }

        return upgrades;
    }

    private void instanceCard(JSONObject card) {
        String cardType = (String) card.get("type");
        String cardName = (String) card.get("name");
        int id = (int) card.get("id");
        int cost = (int) card.get("cost");

        JSONArray effectJSON = (JSONArray) card.get("effects");

        if (cardType.equalsIgnoreCase("CHAMPION")) {
            List<Integer> cardStatus = cardStatus(card);
            List<String> cardEffect = cardEffect(effectJSON);

            JSONObject traitJSON = (JSONObject) card.get("trait");
            List<String> cardTrait = cardTrait(traitJSON);

            JSONObject typeChampion = (JSONObject) card.get("levelup");

            int pointsUpgrade = (int) typeChampion.get("points");

            List<String> upgrades = cardUpgrade(typeChampion);
        } else if(cardType.equalsIgnoreCase("FOLLOWER")) {
            List<Integer> cardStatus = cardStatus(card);
            List<String> cardEffect = cardEffect(effectJSON);
        } else {
            List<String> cardEffect = cardEffect(effectJSON);
        }
    }
}
