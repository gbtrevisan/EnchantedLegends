package com.unicamp.mc322.enchantedlegends.game.filemanager.json.converter;

import com.unicamp.mc322.enchantedlegends.game.card.trait.DoubleAttackTrait;
import com.unicamp.mc322.enchantedlegends.game.card.trait.ElusiveTrait;
import com.unicamp.mc322.enchantedlegends.game.card.trait.FuryTrait;
import com.unicamp.mc322.enchantedlegends.game.card.trait.Trait;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public abstract class JsonConverterToUnit extends JsonConverterToCard {
    protected int damage;
    protected int health;
    protected int cost;
    protected String name;
    protected Trait cardTrait;
    protected List<String> cardEffects;

    protected JsonConverterToUnit() {
        this.cardEffects = new ArrayList<>();
    }

    protected void getUnitInformation(String json) throws ParseException {
        JSONObject jsonFIile = (JSONObject) this.parser.parse(json);

        this.name = getClassStringAttribute(jsonFIile, "name");
        this.cost = getClassIntAttribute(jsonFIile, "cost");
        this.health = getClassIntAttribute(jsonFIile, "health");
        this.damage = getClassIntAttribute(jsonFIile, "damage");

        JSONArray effects = (JSONArray) jsonFIile.get("effects") ;
        this.cardEffects = loadEffect(effects);

        JSONObject traitJSON = (JSONObject) jsonFIile.get("trait");
        this.cardTrait = loadTrait(traitJSON);
    }

    protected Trait loadTrait(JSONObject traitJSON) {
        Trait trait = null;

        if (traitJSON != null) {
            String traitType = (String) traitJSON.get("type");

            if (traitType.equalsIgnoreCase("FURY")) {
                int health = getClassIntAttribute(traitJSON, "health");
                int damage = getClassIntAttribute(traitJSON, "damage");

                trait = new FuryTrait(damage, health);
            } else if (traitType.equalsIgnoreCase("DOUBLE_ATTACK")) {
                trait = new DoubleAttackTrait();
            } else {
                trait = new ElusiveTrait();
            }
        }

        return trait;
    }
}
