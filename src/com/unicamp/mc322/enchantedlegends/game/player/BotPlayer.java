package com.unicamp.mc322.enchantedlegends.game.player;

public class BotPlayer extends Player {

    public BotPlayer(String name) {
        super(name);
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
