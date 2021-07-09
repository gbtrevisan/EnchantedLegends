package com.unicamp.mc322.enchantedlegends.game.filemanager.json.converter;

import com.unicamp.mc322.enchantedlegends.game.card.trait.Trait;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.Champion;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.types.*;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.*;
import com.unicamp.mc322.enchantedlegends.game.effect.Effect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class JsonConverterToChampion extends JsonConverterToUnit {
    protected JsonConverterToChampion() {
    }

    @Override
    protected Champion getJSONObject(String json) throws ParseException {
        getUnitInformation(json);
        JSONObject jsonFIile = (JSONObject) this.parser.parse(json);

        JSONObject typeChampion = (JSONObject) jsonFIile.get("levelup");
        String championType = getClassStringAttribute(typeChampion, "type");
        int pointsUpgrade = getClassIntAttribute(typeChampion, "points");

        List<ChampionUpgrade> upgrades = loadCardUpgrade(typeChampion);

        return null; //createChampion(championType, name, cost, damage, health, pointsUpgrade, upgrades, cardTrait, cardEffect);
    }

    private Champion createChampion(String type, String cardName, int cost, int damage, int health, int points, List<ChampionUpgrade> championUpgrades, Trait trait, Effect... effects) {
        Champion champion = null;

        if (type.equalsIgnoreCase("ATTACK_ENEMY")) {
            champion = new AttackToLevelUpChampion(cardName, cost, damage, health, points, championUpgrades, trait, effects);
        } else if (type.equalsIgnoreCase("RECIEVE_DAMAGE")) {
            champion = new ReciveDamageToLevelUpChampion(cardName, cost, damage, health, points, championUpgrades, trait, effects);
        } else if (type.equalsIgnoreCase("CAUSE_DAMAGE")) {
            champion = new CauseDamageToLevelUpChampion(cardName, cost, damage, health, points, championUpgrades, trait, effects);
        } else if (type.equalsIgnoreCase("KILL_ENEMY")) {
            champion = new KillToLevelUpChampion(cardName, cost, damage, health, points, championUpgrades, trait, effects);
        }

        return champion;
    }

    private List<ChampionUpgrade> loadCardUpgrade(JSONObject card) {
        JSONArray powerUps = (JSONArray) card.get("powerups");
        List<ChampionUpgrade> upgrades = new ArrayList<>();

        for (Object power : powerUps) {
            JSONObject powerUp = (JSONObject) power;

            String type = (String) powerUp.get("type");

            if (type.equalsIgnoreCase("HEALTH")) {
                int healthPoints = getClassIntAttribute(powerUp, "health");
                upgrades.add(new HealthUpgrade(healthPoints));
            } else if (type.equalsIgnoreCase("DAMAGE")) {
                int damagePoints = getClassIntAttribute(powerUp, "damage");
                upgrades.add(new DamageUpgrade(damagePoints));
            } else if (type.equalsIgnoreCase("EFFECT")) {
                JSONArray effect = (JSONArray) powerUp.get("effect");
                // ARRUMAR QUANDO TIVER EFFECTS
                //upgrades.add(new MagicUpgrade(null));
                //upgrades.addAll(cardEffect(effect));
            } else if (type.equalsIgnoreCase("TRAIT")) {
                JSONObject trait = (JSONObject) powerUp.get("trait");
                Trait powerUpTrait = loadTrait(trait);
                upgrades.add(new MagicUpgrade(powerUpTrait));
            }
        }

        return upgrades;
    }
}
