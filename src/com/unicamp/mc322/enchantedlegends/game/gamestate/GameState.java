package com.unicamp.mc322.enchantedlegends.game.gamestate;

import com.unicamp.mc322.enchantedlegends.game.player.Player;

public class GameState {
    private Player self, enemy;

    public Player getSelf() {
        return self;
    }

    public Player getEnemy() {
        return enemy;
    }
}
