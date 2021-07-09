package com.unicamp.mc322.enchantedlegends.game.filemanager.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonLoader {
    private static JsonLoader instance;

    private JsonLoader() {
    }

    public static JsonLoader getInstance() {
        if (instance == null) {
            instance = new JsonLoader();
        }

        return instance;
    }

    public String loadJson(String fileName) throws IOException, ParseException {
        FileReader fileReader = new FileReader(String.valueOf(getClass().getResource(fileName).getFile()));
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonFile = (JSONObject) jsonParser.parse(fileReader);

        return  jsonFile.toJSONString();
    }
}
