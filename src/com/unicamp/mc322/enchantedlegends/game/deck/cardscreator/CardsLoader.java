package com.unicamp.mc322.enchantedlegends.game.deck.cardscreator;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.card.trait.DoubleAttackTrait;
import com.unicamp.mc322.enchantedlegends.game.card.trait.ElusiveTrait;
import com.unicamp.mc322.enchantedlegends.game.card.trait.FuryTrait;
import com.unicamp.mc322.enchantedlegends.game.card.trait.Trait;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.ChampionUpgrade;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.DamageUpgrade;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.HealthUpgrade;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.MagicUpgrade;
import com.unicamp.mc322.enchantedlegends.game.effect.Effect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardsLoader {
    private static CardsLoader instance;
    private final static String PATH_JSON = "src/com/unicamp/mc322/enchantedlegends/game/deck/cardscreator/all_cards.json";
    private final Map<Integer, Card> gameCards;

    private CardsLoader() {
        this.gameCards = new HashMap<>();
    }

    public static CardsLoader getInstance() {
        if (instance == null) {
            instance = new CardsLoader();
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

        statusCard.add((int) card.get("damage"));
        statusCard.add((int) card.get("health"));

        return statusCard;
    }

    private String createEffect(JSONObject effect) {
        String effecType = (String) effect.get("type");

        if (effecType.equalsIgnoreCase("BOOST_ALL_UNITS")) {
            return "BOOST_ALL_UNITS";
        } else if (effecType.equalsIgnoreCase("BOOST_UNIT")) {
            int healthPoints = (int) effect.get("health");
            int damagePoints = (int) effect.get("damage");

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

    private List<Trait> cardTrait(JSONObject traitJSON) {
        List<Trait> trait = new ArrayList<>();

        if (traitJSON != null) {
            String traitType = (String) traitJSON.get("type");

            if (traitType.equalsIgnoreCase("FURY")) {
                int health = (int) traitJSON.get("health");
                int damage = (int) traitJSON.get("damage");

                trait.add(new FuryTrait(damage, health));
            } else if (traitType.equalsIgnoreCase("DOUBLE_ATTACK")) {
                trait.add(new DoubleAttackTrait());
            } else {
                trait.add(new ElusiveTrait());
            }
        }

        return trait;
    }

    private List<ChampionUpgrade> cardUpgrade(JSONObject card) {
        JSONArray powerUps = (JSONArray) card.get("powerups");
        List<ChampionUpgrade> upgrades = new ArrayList<>();

        for (Object o : powerUps) {
            JSONObject powerUp = (JSONObject) o;

            String type = (String) powerUp.get("type");

            if (type.equalsIgnoreCase("HEALTH")) {
                int healthPoints = (int) powerUp.get("health");

                upgrades.add(new HealthUpgrade(healthPoints));
            } else if (type.equalsIgnoreCase("DAMAGE")) {
                int damagePoints = (int) powerUp.get("damage");

                upgrades.add(new DamageUpgrade(damagePoints));
            } else if (type.equalsIgnoreCase("EFFECT")) {
                JSONArray effect = (JSONArray) powerUp.get("effect");
                // ARRUMAR QUANDO TIVER EFFECTS
                //upgrades.add(new MagicUpgrade(null));
                //upgrades.addAll(cardEffect(effect));
            } else {
                JSONObject trait = (JSONObject) powerUp.get("trait");
                Trait powerUpTrait = cardTrait(trait).get(0);

                upgrades.add(new MagicUpgrade(powerUpTrait));
            }
        }

        return upgrades;
    }

    private void createChampion(String type, int id, String cardName, int cost, int damage, int health, List<Trait> traits, Effect... effects) {
        if (type.equalsIgnoreCase("ATTACK_ENEMY")) {
            //RECIEVE_DAMAGE CAUSE_DAMAGE
        } else if (type.equalsIgnoreCase("RECIEVE_DAMAGE")) {

        } else {

        }
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
            List<Trait> cardTrait = cardTrait(traitJSON);

            JSONObject typeChampion = (JSONObject) card.get("levelup");
            int pointsUpgrade = (int) typeChampion.get("points");

            List<ChampionUpgrade> upgrades = cardUpgrade(typeChampion);
        } else if(cardType.equalsIgnoreCase("FOLLOWER")) {
            List<Integer> cardStatus = cardStatus(card);
            List<String> cardEffect = cardEffect(effectJSON);

            JSONObject traitJSON = (JSONObject) card.get("trait");
            List<Trait> cardTrait = cardTrait(traitJSON);

            //this.gameCards.put(id, new Follower(id, cardName, cost, cardStatus.get(0), cardStatus.get(1), cardTrait, cardEffect));
        } else {
            List<String> cardEffect = cardEffect(effectJSON);
        }
    }
}
