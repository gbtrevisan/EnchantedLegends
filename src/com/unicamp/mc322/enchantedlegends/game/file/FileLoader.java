package com.unicamp.mc322.enchantedlegends.game.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileLoader {
    private static FileLoader instance;

    private FileLoader() {
    }

    public static FileLoader getInstance() {
        if (instance == null) {
            instance = new FileLoader();
        }

        return instance;
    }

    public String loadFileAsString(Class<?> clazz, String path) {
        try (Scanner scanner = new Scanner(clazz.getResource(path).openStream(), StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
