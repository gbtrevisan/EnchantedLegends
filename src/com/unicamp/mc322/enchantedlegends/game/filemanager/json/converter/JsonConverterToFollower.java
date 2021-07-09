package com.unicamp.mc322.enchantedlegends.game.filemanager.json.converter;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import org.json.simple.parser.ParseException;

public class JsonConverterToFollower extends JsonConverterToUnit {
    protected JsonConverterToFollower() {
    }

    @Override
    protected Follower getJSONObject(String json) throws ParseException {
        getUnitInformation(json);
        return null;//new Follower(this.name, this.cost, this.damage, this.health, this.cardTrait, this.cardEffect);
    }

    protected Follower getFollower(String json) throws ParseException {
        return getJSONObject(json);
    }
}
