package com.unicamp.mc322.enchantedlegends.game.player.types;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.player.Player;
import com.unicamp.mc322.enchantedlegends.game.player.io.ConsolePlayerIO;
import com.unicamp.mc322.enchantedlegends.game.player.io.options.PlayerActions;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {

    private final ConsolePlayerIO playerIO;

    public HumanPlayer(String name) {
        super(name);
        this.playerIO = new ConsolePlayerIO();
    }

    @Override
    public PlayerActions chooseAction() {
        if (this.onAttack) {
            this.playerIO.displayAttackerActions();
            return playerIO.chooseAttackerAction();
        } else {
            this.playerIO.displayDefenserActions();
            return playerIO.chooseDefenserAction();
        }
    }

    @Override
    public void changeInitialHand() {
        this.playerIO.displayChangeInitialHandMessage();
        this.playerIO.displayTip();
        PlayerActions playerAction = playerIO.changeInitialHandOptions();

        if (playerAction == PlayerActions.CHANGE_INITIAL_HAND) {
            this.playerCards.changeInitialHand(changeCards());
        }
    }

    @Override
    public int chooseEvokeUnit() {
        for (int i = 0; i < this.playerCards.getNumberOfEvokedUnits(); i++) {
            this.playerIO.displayCard(i + 1, this.playerCards.getSelectedEvokedUnit(i));
        }

        return this.playerIO.chooseCardOption();
    }

    /*@Override
    public int chooseCombatUnit() {
        return chooseEvokeUnit();
    }*/

    @Override
    public void discardUnit(int unitToDiscard, Follower follower) {
        for (int i = 0; i < this.playerCards.getNumberOfEvokedUnits(); i++) {
            this.playerIO.displayCard(i + 1, this.playerCards.getSelectedEvokedUnit(i));
        }

        this.playerIO.displayEvokeAreaActions();
        PlayerActions playerAction = this.playerIO.chooseEvokeAreaActions();

        if (playerAction == PlayerActions.DISCARD_UNIT) {
            Follower removedUnit = this.playerCards.replaceEvokedUnit(unitToDiscard, follower);
            this.restoreMana(removedUnit.getCost());
        } else {
            this.restoreMana(follower.getCost());
        }
    }

    @Override
    public boolean insertUnitToCombat() {
        this.playerIO.displayTip();
        return this.playerIO.cancelOperation(this.playerIO.chooseCardOption());
    }

    @Override
    public int chooseHandCard() {
        for (int i = 0; i < this.playerCards.getHandSize(); i++) {
            this.playerIO.displayCard(i + 1, this.playerCards.getSelectedHandCard(i));
        }

        return this.playerIO.chooseCardOption();
    }

    @Override
    public int chooseEnemyCard(Player enemey) {
        for (int i = 0; i < enemey.getPlayerCards().getNumberOfEvokedUnits(); i++) {
            playerIO.displayCard(i + 1, enemey.getPlayerCards().getSelectedEvokedUnit(i));
        }

        return this.playerIO.chooseCardOption();
    }

    @Override
    public void chooseDeck() {

    }

    private List<Integer> changeCards() {
        List<Integer> cardsToChange = new ArrayList<>();
        int cardToChange = 0, numberOfChanges = 0;

        do {
            this.playerIO.displaySelectCardMessage();
            cardToChange = playerIO.chooseCardOption();

            if (cardIsValid(cardToChange) && isUnique(cardToChange, cardsToChange)) {
                cardsToChange.add(cardToChange);
                numberOfChanges++;
            }
        } while (this.playerIO.cancelOperation(cardToChange) && numberOfChanges < this.playerCards.getHandSize());

        return cardsToChange;
    }

    private boolean cardIsValid(int cardIndex) {
        return cardIndex >= 1 && cardIndex <= this.playerCards.getHandSize();
    }

    private boolean isUnique(int cardIndex, List<Integer> curentChanges) {
        for (Integer curentChange : curentChanges) {
            if (curentChange == cardIndex) {
                return false;
            }
        }

        return true;
    }
}