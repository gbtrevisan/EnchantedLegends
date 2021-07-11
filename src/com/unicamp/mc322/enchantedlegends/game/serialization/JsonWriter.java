package com.unicamp.mc322.enchantedlegends.game.serialization;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public abstract class JsonWriter {
    protected final JSONParser parser;

    public JsonWriter() {
        this.parser = new JSONParser();
    }

    public abstract void saveObject(Object toSave) throws ParseException, IOException;
}
