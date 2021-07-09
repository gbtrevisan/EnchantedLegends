package com.unicamp.mc322.enchantedlegends.game.filemanager.converter;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public abstract  class JsonConverter {
   protected final JSONParser parser;

    public JsonConverter() {
        this.parser = new JSONParser();
    }

    protected abstract Object getJSONObject(FileReader json) throws ParseException, IOException;
}
