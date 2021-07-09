package com.unicamp.mc322.enchantedlegends.game.filemanager.converter;

import com.unicamp.mc322.enchantedlegends.game.card.trait.DoubleAttackTrait;
import com.unicamp.mc322.enchantedlegends.game.card.trait.ElusiveTrait;
import com.unicamp.mc322.enchantedlegends.game.card.trait.FuryTrait;
import com.unicamp.mc322.enchantedlegends.game.card.trait.Trait;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
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

    protected void getUnitInformation(FileReader json) throws ParseException, IOException {
        JSONObject jsonFIile = (JSONObject) this.parser.parse(json);

        this.name = (String) jsonFIile.get("name");
        this.cost = getClassAtribute(jsonFIile, "cost");
        this.health = getClassAtribute(jsonFIile, "health");
        this.damage = getClassAtribute(jsonFIile, "damage");

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
                int health = getClassAtribute(traitJSON, "health");
                int damage = getClassAtribute(traitJSON, "damage");

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
