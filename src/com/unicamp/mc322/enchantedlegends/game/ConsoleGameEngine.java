package com.unicamp.mc322.enchantedlegends.game;

import com.unicamp.mc322.enchantedlegends.game.player.Player;

import java.util.Objects;

public class ConsoleGameEngine implements GameEngine {

    private final ConsoleGameIO io = new ConsoleGameIO();
    private Player attacker;
    private Player defender;
    private int roundCounter;


    public ConsoleGameEngine(Player player1, Player player2) {
        Objects.requireNonNull(player1, "Player1 must not be null");
        Objects.requireNonNull(player2, "Player2 must not be null");

        attacker = player1;
        defender = player2;
        roundCounter = 0;
    }

    @Override
    public void run() {
        io.displayGameBeginMessage();
        attacker.receiveInitialHand();
        defender.receiveInitialHand();

        attacker.changeInitialHand();
        defender.changeInitialHand();

        do {
            newRound();
        } while (attacker.isAlive() && defender.isAlive());

        if (attacker.isAlive()) {
            io.displayCongratulations(attacker, defender);
        } else {
            io.displayCongratulations(defender, attacker);
        }
    }

    private void newRound() {
        roundCounter++;
        attacker.restoreMana(roundCounter);
        attacker.restoreMana(roundCounter);
        attacker.gainRandomCard();
        defender.gainRandomCard();
        attacker.startTurn();

        while (!attacker.chosenToAttack()) {
            defender.startTurn();
            attacker.startTurn();
        }

        attacker.attack(defender);

        Player aux = attacker;
        attacker = defender;
        defender = aux;
    }
}
