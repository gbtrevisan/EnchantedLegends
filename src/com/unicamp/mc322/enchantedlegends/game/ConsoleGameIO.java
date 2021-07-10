package com.unicamp.mc322.enchantedlegends.game;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.card.PlayerCards;
import com.unicamp.mc322.enchantedlegends.game.deck.concrete.Deck;
import com.unicamp.mc322.enchantedlegends.game.player.Player;

import java.util.Scanner;

public class ConsoleGameIO implements GameObjectVisitor {

    private final static Scanner scanner = new Scanner(System.in);

    void displayGameBeginMessage() {

    }

    void displayNewRoundMessage() {

    }

    void displayNewTurnMessage() {

    }

    void displayCongratulations(Player winner, Player loser) {

    }

    @Override
    public void visit(Player player) {

    }

    @Override
    public void visit(PlayerCards cards) {

    }

    @Override
    public void visit(Card card) {

    }

    @Override
    public void visit(Deck deck) {

    }
}
