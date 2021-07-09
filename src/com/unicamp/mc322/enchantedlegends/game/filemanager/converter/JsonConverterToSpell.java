package com.unicamp.mc322.enchantedlegends.game.filemanager.converter;

import com.unicamp.mc322.enchantedlegends.game.card.Spell;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JsonConverterToSpell extends JsonConverterToCard {
    protected JsonConverterToSpell() {
    }

    @Override
    protected Spell getJSONObject(FileReader json) throws ParseException, IOException {
        JSONObject jsonFIile = (JSONObject) this.parser.parse(json);
        String name = (String) jsonFIile.get("name");

        int cost = getClassAtribute(jsonFIile, "cost");

        JSONArray effects = (JSONArray) jsonFIile.get("effects");
        List<String> cardEffect = loadEffect(effects);

        return null; //new Spell(...);
    }



    protected Spell getSpell(FileReader json) throws ParseException, IOException {
        return (Spell) getJSONObject(json);
    }
}
