package com.unicamp.mc322.enchantedlegends.game.filemanager.json.converter;

import com.unicamp.mc322.enchantedlegends.game.card.Spell;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

public class JsonConverterToSpell extends JsonConverterToCard {
    protected JsonConverterToSpell() {
    }

    @Override
    protected Spell getJSONObject(String json) throws ParseException {
        JSONObject jsonFIile = (JSONObject) this.parser.parse(json);
        String name = getClassStringAttribute(jsonFIile, "name");

        int cost = getClassIntAttribute(jsonFIile, "cost");

        JSONArray effects = (JSONArray) jsonFIile.get("effects");
        List<String> cardEffect = loadEffect(effects);

        return null; //new Spell(...);
    }



    protected Spell getSpell(String json) throws ParseException {
        return (Spell) getJSONObject(json);
    }
}
