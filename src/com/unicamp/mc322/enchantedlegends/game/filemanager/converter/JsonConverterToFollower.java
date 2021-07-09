package com.unicamp.mc322.enchantedlegends.game.filemanager.converter;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonConverterToFollower extends JsonConverterToUnit {
    protected JsonConverterToFollower() {
    }

    @Override
    protected Follower getJSONObject(FileReader json) throws ParseException, IOException {
        getUnitInformation(json);
        return null;//new Follower(this.name, this.cost, this.damage, this.health, this.cardTrait, this.cardEffect);
    }

    protected Follower getFollower(FileReader json) throws ParseException, IOException {
        return getJSONObject(json);
    }
}
