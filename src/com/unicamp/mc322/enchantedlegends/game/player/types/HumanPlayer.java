package com.unicamp.mc322.enchantedlegends.game.player.types;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.player.Player;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {

    public HumanPlayer() {
        super();
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