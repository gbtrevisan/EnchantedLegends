package com.unicamp.mc322.enchantedlegends.game.player.types;

import com.unicamp.mc322.enchantedlegends.game.player.Player;

public class HumanPlayer extends Player {
    public HumanPlayer() {
        super();
    }

    public void changeInitialHand() {
        // I/O

        this.playerCards.changeInitialHand(20000000);
    }

    @Override
    public int chooseHandCard() {
        // I/O
        return 0;
    }

    @Override
    public int chooseCard() {
        // I/O
        return 0;
    }

    @Override
    public int chooseEnemyCard(Player enemey) {
        // I/O
        return 0;
    }

    @Override
    public void chooseDeck() {

    }
}
