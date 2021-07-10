package com.unicamp.mc322.enchantedlegends.game.player.types;

import com.unicamp.mc322.enchantedlegends.game.player.Player;

public class BotPlayer extends Player {

    public BotPlayer() {
        super();
    }

    @Override
    public int choosePositonToEvokeUnit() {
        return this.playerCards.getRandomEvokePosition();
    }

    @Override
    public int chooseHandCard() {
        return this.playerCards.randomIndex(this.playerCards.getHandSize());
    }

    @Override
    public int chooseCard() {
        return this.playerCards.randomIndex(this.playerCards.getNumberOfEvokedUnits());
    }

    @Override
    public int chooseEnemyCard(Player enemey) {
        return enemey.getPlayerCards().randomIndex(enemey.getPlayerCards().getNumberOfEvokedUnits());
    }

    @Override
    public void chooseDeck() {
    }
}