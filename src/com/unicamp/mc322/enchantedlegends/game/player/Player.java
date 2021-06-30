package com.unicamp.mc322.enchantedlegends.game.player;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.card.mana.Mana;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

import java.util.List;

public class Player {
    private List<Card> cardsOnDeck;
    private List<Card> cardsOnHand;
    private List<Follower> evokedUnits;

    private Mana mana;

    public void addToEvokedUnits(Follower follower) {
        if (cardsOnHand.contains(follower)) {
            cardsOnHand.remove(follower);
            evokedUnits.add(follower);
        }
    }

    public Mana getMana() {
        return mana;
    }
}
