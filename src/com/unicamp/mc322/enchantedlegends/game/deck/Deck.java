package com.unicamp.mc322.enchantedlegends.game.deck;

import com.unicamp.mc322.enchantedlegends.game.card.Card;

import java.util.List;

public interface Deck {
    //void addNewCard(Card card);

    List<Card> changeCard(List<Card> initialHand, int numberOfChanges);

    List<Card> getInitialHand();
}