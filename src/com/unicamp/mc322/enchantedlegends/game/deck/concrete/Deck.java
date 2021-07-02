package com.unicamp.mc322.enchantedlegends.game.deck.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.deck.DeckException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> deckCards;
    private final static int MAX_CARDS = 40;
    private final static int INITIAL_HAND = 4;

    public Deck(List<Card> deckCards) {
        if (deckCards.isEmpty() || deckCards.size() > MAX_CARDS) {
            throw new DeckException("Your deck cannot be empty or over " + MAX_CARDS + " cards");
        }

        this.deckCards = new ArrayList<>();
    }

    public List<Card> getInitialHand() {
        List<Card> initialHand = new ArrayList<>();

        for (int x = 0; x < INITIAL_HAND && !this.deckCards.isEmpty(); x++) {
            Card randomCard = this.getRandomCard(this.deckCards);
            this.deckCards.remove(randomCard);
            initialHand.add(randomCard);
        }

        return initialHand;
    }


    public List<Card> changeCard(List<Card> initialHand, int numberOfChanges) {
        if (numberOfChanges < 0 || numberOfChanges > initialHand.size()) {
            throw new DeckException("You can trade from 0 to " + initialHand.size() + " cards");
        }

        List<Card> newHandCards = new ArrayList<>(), changedCards = new ArrayList<>();

        for (int x = 0; x < numberOfChanges && !this.deckCards.isEmpty(); x++) {
            Card randomHandCard = this.getRandomCard(initialHand);
            changedCards.add(randomHandCard);
            initialHand.remove(randomHandCard);
            newHandCards.add(this.getRandomCard(this.deckCards));
        }

        initialHand.addAll(newHandCards);
        this.deckCards.addAll(changedCards);

        return initialHand;
    }

    public Card getCard() {
        Card newCard = this.getRandomCard(this.deckCards);

        this.deckCards.remove(newCard);

        return newCard;
    }

    private int getRandomIndex(int deckSize) {
        Random random = new Random();

        return random.nextInt(deckSize);
    }

    private Card getRandomCard(List<Card> cards) {
        if (cards.isEmpty()) {
            throw new DeckException("Your deck is empty");
        }

        int cardIndex = getRandomIndex(cards.size());

        return cards.get(cardIndex);
    }
}
