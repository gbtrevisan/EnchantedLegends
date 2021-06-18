package com.unicamp.mc322.enchantedlegends.game;

public class GameState {
    private static GameState instance;

    private GameState() {

    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        
        return instance;
    }
}
