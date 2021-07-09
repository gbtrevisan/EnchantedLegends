package com.unicamp.mc322.enchantedlegends.game.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public File loadDirectory(String path) {
        return new File(path);
    }

    public FileReader loadFile(Class<?> clazz, String path) throws FileNotFoundException {
        return new FileReader(String.valueOf(clazz.getResource(path).getFile()));
    }

    public String loadFileAsString(Class<?> clazz, String path) {
        try (Scanner scanner = new Scanner(clazz.getResource(path).openStream(), StandardCharsets.UTF_8.toString()))
        {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
