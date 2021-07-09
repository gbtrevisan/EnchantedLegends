package com.unicamp.mc322.enchantedlegends.game.filemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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

    public FileReader loadFile(String path) throws FileNotFoundException {
        FileReader fileReader = new FileReader(String.valueOf(getClass().getResource(path).getFile()));

        return fileReader;
    }
}
