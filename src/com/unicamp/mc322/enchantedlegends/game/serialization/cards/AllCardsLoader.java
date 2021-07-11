package com.unicamp.mc322.enchantedlegends.game.serialization.cards;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.card.Spell;
import com.unicamp.mc322.enchantedlegends.game.card.effect.concrete.BoostAllUnitsEffect;
import com.unicamp.mc322.enchantedlegends.game.card.effect.concrete.NewUnitCardEffect;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.types.AttackToLevelUpChampion;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.types.ReceiveDamageToLevelUpChampion;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.DamageUpgrade;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.HealthUpgrade;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.MagicUpgrade;
import com.unicamp.mc322.enchantedlegends.game.card.unit.trait.concrete.ElusiveTrait;
import com.unicamp.mc322.enchantedlegends.game.card.unit.trait.concrete.FuryTrait;
import com.unicamp.mc322.enchantedlegends.game.file.FileLoader;
import com.unicamp.mc322.enchantedlegends.game.serialization.JsonLoader;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class AllCardsLoader extends JsonLoader {
    private static AllCardsLoader instance;
    private final static String JSON_CARDS_FILENAME = "all_cards.json";

    public static AllCardsLoader getInstance() {
        if (instance == null) {
            instance = new AllCardsLoader();
        }

        return instance;
    }

    public void createAllCardsByDefault() {
        List<Card> gameCards = Arrays.asList(
                new AttackToLevelUpChampion("Garen", 5, 5, 5, 2, Arrays.asList(new MagicUpgrade(new ElusiveTrait()), new DamageUpgrade(1), new HealthUpgrade(1))),
                new Follower("Vanguarda", 4, 3, 3, Collections.singletonList(new BoostAllUnitsEffect(1, 1))),
                new Follower("Duelista", 3, 3, 2, Collections.singletonList(new NewUnitCardEffect("Poro"))),
                //new Follower("Defensor", 2, 2, 2, Collections.singletonList(new FuryTrait(0, 1))),
                new Follower(),
                new Follower(),
                new Spell(),
                new Spell(),
                new Spell(),
                new Spell(),
                new ReceiveDamageToLevelUpChampion(),
                new AttackToLevelUpChampion(),
                new Follower(),
                new Follower(),
                new Follower(),
                new Follower(),
                new Spell(),
                new Spell(),
                new Spell(),
                new Spell(),
                new AttackToLevelUpChampion(),
                new Follower(),
                new Follower(),
                new Follower(),
                new Follower()
        );

        gameCards.forEach(gameCard -> GameCards.getInstance().addCard(gameCard));
    }

    public void createAllCardsFromJson() {
        try {
            List<Card> cardList = parseToObject(FileLoader.getInstance().loadFileAsString(getClass(), JSON_CARDS_FILENAME));
            cardList.forEach(card -> GameCards.getInstance().addCard(card));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
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
