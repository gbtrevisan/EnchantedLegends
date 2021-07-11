package com.unicamp.mc322.enchantedlegends.game.player.cards;

import com.unicamp.mc322.enchantedlegends.game.GameObject;
import com.unicamp.mc322.enchantedlegends.game.GameObjectVisitor;
import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.cards.GameCards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerCards implements GameObject {

    private final List<Card> playerUnseenCards;
    private final List<Card> playerHand;
    private final EvokedUnits evokedUnits;
    private final List<Card> deadUnits;
    private final static int INITIAL_HAND = 4;

    public PlayerCards() {
        this.evokedUnits = new EvokedUnits();
        this.playerUnseenCards = new ArrayList<>();
        this.playerHand = new ArrayList<>();
        this.deadUnits = new ArrayList<>();
    }

    public List<Follower> getEvokedUnits() {
        return this.evokedUnits.getEvokedUnits();
    }

    public void getRandomCard() {
        Card randomCard = this.playerUnseenCards.get(randomIndex(this.playerUnseenCards.size()));
        this.playerHand.add(randomCard);
        this.playerUnseenCards.remove(randomCard);
        checkCardsSituation();
    }

    public void getInitialHand() {
        for (int i = 0; i < INITIAL_HAND && !this.playerUnseenCards.isEmpty(); i++) {
            getRandomCard();
        }
    }

    public void changeInitialHand(List<Integer> cardsToChange) {
        List<Card> removedHandCards = new ArrayList<>(), newHandCards = new ArrayList<>();

        for (int i = 0; i < cardsToChange.size() && !this.playerUnseenCards.isEmpty(); i++) {
            removedHandCards.add(this.playerHand.get(cardsToChange.get(i)));
            Card newCard = this.playerUnseenCards.get(randomIndex(this.playerUnseenCards.size()));
            newHandCards.add(newCard);

            this.playerUnseenCards.remove(newCard);
            checkCardsSituation();
        }

        this.playerHand.removeAll(removedHandCards);
        this.playerHand.addAll(newHandCards);
        this.playerUnseenCards.addAll(removedHandCards);
    }

    public Follower replaceEvokedUnit(int unitIndex, Follower handUnit) {
        if (isIndexInvalid(unitIndex, this.evokedUnits.getNumberOfEvokedUnits())) {
            throw new PlayerCardException("The desired index " + unitIndex + " is invalid");
        }

        Follower removedUnit = this.evokedUnits.removeUnit(unitIndex);
        this.playerHand.add(removedUnit);
        this.evokedUnits.evokeNewUnit(handUnit);

        return removedUnit;
    }

    public void evokeUnit(Follower followerEvoked) {
        this.evokedUnits.evokeNewUnit(followerEvoked);
    }

    public void gainDeckCard(String cardName) {
        this.playerHand.add(GameCards.getInstance().getByName(cardName));
    }

    public Card getSelectedHandCard(int cardIndex) {
        if (isIndexInvalid(cardIndex, this.playerHand.size())) {
            throw new PlayerCardException("The desired index " + cardIndex + " is invalid");
        }

        return this.playerHand.get(cardIndex - 1);
    }

    public Follower getSelectedEvokedUnit(int cardIndex) {
        if (isIndexInvalid(cardIndex, this.evokedUnits.getNumberOfEvokedUnits())) {
            throw new PlayerCardException("The desired index " + cardIndex + " is invalid");
        }

        return this.evokedUnits.getSelectedEvokedUnit(cardIndex);
    }

    public int getHandSize() {
        return this.playerHand.size();
    }

    public int randomIndex(int listSize) {
        Random random = new Random();

        return random.nextInt(listSize);
    }

    public int getNumberOfEvokedUnits() {
        return this.evokedUnits.getNumberOfEvokedUnits();
    }

    public boolean isEvokedPositionFull() {
        return this.evokedUnits.isEvokedPositionFull();
    }

    private boolean isIndexInvalid(int index, int maxValue) {
        return index < 1 || index > maxValue;
    }

    private void checkCardsSituation() {
        if (this.playerUnseenCards.isEmpty() && this.playerHand.isEmpty()) {
            restartPlayerCards();
        }
    }

    private void restartPlayerCards() {
        this.playerUnseenCards.addAll(this.deadUnits);
        this.deadUnits.clear();
    }

    @Override
    public void accept(GameObjectVisitor visitor) {

    }
}