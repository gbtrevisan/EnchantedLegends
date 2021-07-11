package com.unicamp.mc322.enchantedlegends.game;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.player.Player;
import com.unicamp.mc322.enchantedlegends.game.player.cards.PlayerCards;
import com.unicamp.mc322.enchantedlegends.game.serialization.deck.Deck;

public interface GameObjectVisitor {

    void visit(Player player);

    void visit(PlayerCards cards);

    void visit(Card card);

    void visit(Deck deck);
}
