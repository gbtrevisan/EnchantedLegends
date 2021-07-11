package com.unicamp.mc322.enchantedlegends.game.player;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.player.io.options.PlayerActions;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public int chooseEvokeUnit() {
        return 0;
    }

    @Override
    public boolean insertUnitToCombat() {
        return false;
    }

    @Override
    public int chooseHandCard() {
        return 0;
    }

    @Override
    public int chooseEnemyCard(Player enemy) {
        return 0;
    }

    @Override
    public void changeInitialHand() {

    }

    @Override
    public PlayerActions chooseAction() {
        return null;
    }

    @Override
    public void chooseDeck() {

    }

    @Override
    public void discardUnit(int unitToDiscard, Follower follower) {

    }
}
