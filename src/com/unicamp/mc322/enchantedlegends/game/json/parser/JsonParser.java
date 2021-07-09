package com.unicamp.mc322.enchantedlegends.game.json.parser;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public abstract class JsonParser {
    protected final JSONParser parser;

    public JsonParser() {
        this.parser = new JSONParser();
    }

    public abstract Object parseToObject(String json) throws ParseException, IOException;
}
