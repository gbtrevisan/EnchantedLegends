package com.unicamp.mc322.enchantedlegends.game.player.types;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.player.Player;
import com.unicamp.mc322.enchantedlegends.game.player.io.options.PlayerActions;

public class BotPlayer extends Player {

    private static final String BOT_MAIN_NAME = "BOT";

    public BotPlayer() {
        super(BOT_MAIN_NAME);
    }

    @Override
    public void startTurn() {

    }

    @Override
    public void changeInitialHand() {
        
    }

    @Override
    public PlayerActions chooseAction() {
        return null;
    }

    @Override
    public boolean insertUnitToCombat() {
        return false;
    }

    @Override
    public int chooseEvokeUnit() {
        return this.playerCards.randomIndex(this.playerCards.getNumberOfEvokedUnits());
    }

    @Override
    public void discardUnit(int unitToDiscard, Follower follower) {
        Follower removedUnit = this.playerCards.replaceEvokedUnit(unitToDiscard, follower);
        this.restoreMana(removedUnit.getCost());
    }

    @Override
    public int chooseHandCard() {
        return this.playerCards.randomIndex(this.playerCards.getHandSize());
    }

    @Override
    public int chooseEnemyCard(Player enemey) {
        return enemey.getPlayerCards().randomIndex(enemey.getPlayerCards().getNumberOfEvokedUnits());
    }

    @Override
    public void chooseDeck() {
    }
}