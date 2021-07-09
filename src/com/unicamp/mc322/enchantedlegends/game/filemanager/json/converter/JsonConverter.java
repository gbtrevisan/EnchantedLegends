package com.unicamp.mc322.enchantedlegends.game.filemanager.json.converter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public abstract  class JsonConverter {
   protected final JSONParser parser;

    public JsonConverter() {
        this.parser = new JSONParser();
    }

    protected abstract Object getJSONObject(String json) throws ParseException, IOException;

    protected int getClassIntAttribute(JSONObject cardObject, String atributeName) {
        return (int) cardObject.get(atributeName);
    }

    protected String getClassStringAttribute(JSONObject cardObject, String atributeName) {
        return (String) cardObject.get(atributeName);
    }
}
