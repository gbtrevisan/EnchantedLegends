package com.unicamp.mc322.enchantedlegends.game.player.cards;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerCards {
    private final List<Card> playerDeck;
    private final List<Card> playerUnseenCard;
    private final List<Card> playerHand;
    private final List<Card> deadUnits;
    private final List<Follower> evokedUnits;
    private final static int INITIAL_HAND = 4;

    public PlayerCards() {
        this.playerDeck = new ArrayList<>();
        this.playerUnseenCard = new ArrayList<>();
        this.playerHand = new ArrayList<>();
        this.evokedUnits = new ArrayList<>();
        this.deadUnits = new ArrayList<>();
    }

    public List<Follower> getEvokedUnits() {
        return evokedUnits;
    }

    public void getRandomCard() {
        Card randomCard = this.playerUnseenCard.get(randomIndex(this.playerUnseenCard.size()));
        this.playerHand.add(randomCard);
        this.playerUnseenCard.remove(randomCard);
        checkCardsSituation();
    }

    public void getInitialHand() {
        for (int x = 0; x < INITIAL_HAND && !this.playerUnseenCard.isEmpty(); x++) {
            getRandomCard();
        }
    }

    public void changeInitialHand(int cardsToChange) {
        if (cardsToChange < 0 || cardsToChange > this.playerHand.size()) {
            throw new PlayerCardException("You can only trade from 0 to " + this.playerHand.size() + " cards");
        }

        List<Card> removedHandCards = new ArrayList<>(), newHandCards = new ArrayList<>();

        for (int x = 0; x < INITIAL_HAND && !this.playerUnseenCard.isEmpty(); x++) {
            Card randomCardHand = this.playerHand.get(randomIndex(this.playerHand.size()));
            Card newCard = this.playerUnseenCard.get(randomIndex(this.playerUnseenCard.size()));
            removedHandCards.add(randomCardHand);
            newHandCards.add(newCard);

            this.playerHand.remove(randomCardHand);
            this.playerUnseenCard.remove(newCard);
            checkCardsSituation();
        }

        changeCardList(this.playerHand, newHandCards);
        changeCardList(this.playerUnseenCard, removedHandCards);
    }

    public void evokeUnit(Follower followerEvoked) {
        for (Card follower : this.playerHand) {
            if (followerEvoked.getName().equalsIgnoreCase(follower.getName())) {
                this.playerHand.remove(follower);
                this.evokedUnits.add(followerEvoked);
            }
        }
    }

    public void gainDeckCard(String cardName) {
        for (Card card : this.playerDeck) {
            if (card.getName().equalsIgnoreCase(cardName)) {
                this.playerHand.add(card);
            }
        }
    }

    public Card getSelectedHandCard(int cardIndex) {
        if (isIndexValide(cardIndex, this.playerHand.size())) {
            throw new PlayerCardException("The desired index " + cardIndex + " is invalid");
        }

        return this.playerHand.get(cardIndex - 1);
    }

    public Follower getSelectedEvokedUnit(int cardIndex) {
        if (isIndexValide(cardIndex, this.evokedUnits.size())) {
            throw new PlayerCardException("The desired index " + cardIndex + " is invalid");
        }

        return this.evokedUnits.get(cardIndex - 1);
    }

    public int randomIndex(int listSize) {
        Random random = new Random();

        return random.nextInt(listSize);
    }

    public int getNumberOfEvokedUnits() {
        return this.evokedUnits.size();
    }

    public int getHandSize() {
        return this.playerHand.size();
    }

    private void changeCardList(List<Card> listToChange, List<Card> listToAdd) {
        listToChange.addAll(listToAdd);
    }

    private boolean isIndexValide(int index, int maxValue) {
        return index >= 1 && index <= maxValue;
    }

    private void checkCardsSituation() {
        if (this.playerUnseenCard.isEmpty() && this.playerHand.isEmpty()) {
            restartPlayerCards();
        }
    }

    private void restartPlayerCards() {
        this.playerUnseenCard.addAll(this.playerDeck);
        this.deadUnits.clear();
    }
}
