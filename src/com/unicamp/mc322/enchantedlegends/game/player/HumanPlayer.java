package com.unicamp.mc322.enchantedlegends.game.player;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    public void changeInitialHand() {
        List<Integer> cardsToChange = new ArrayList<>();

        // I/O

        this.playerCards.changeInitialHand(cardsToChange);
    }

    @Override
    public int choosePositonToEvokeUnit() {
        // I/O
        return 0;
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
