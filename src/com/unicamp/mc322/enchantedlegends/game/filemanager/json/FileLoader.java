package com.unicamp.mc322.enchantedlegends.game.filemanager.json;

import java.io.File;

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
}
