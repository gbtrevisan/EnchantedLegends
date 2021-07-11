package com.unicamp.mc322.enchantedlegends.game.player.io;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.player.io.options.OptionsException;
import com.unicamp.mc322.enchantedlegends.game.player.io.options.PlayerActions;

import java.util.Scanner;

public class ConsolePlayerIO implements PlayerIO {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int CANCEL_OPERATION = -1;

    @Override
    public void displayAttackerActions() {
        System.out.println("[1] - CHOOSE A HAND CARD\n[2] - PASS TURN\n[3] - ATTACK\n[4] - FINISH ROUN WITHOUT ATTACK");
    }

    @Override
    public void displayDefenserActions() {
        System.out.println("[1] - CHOOSE A HAND CARD\n[2] - PASS TURN");
    }

    @Override
    public void displayChangeInitialHandMessage() {
        System.out.println("DO YOU WANT TO CHANGE YOUR INITIAL HAND?\n[1] - YES\n[2] - NO");
    }

    @Override
    public void displayTip() {
        System.out.println("TIP: IF YOU SELECTED [1], AFTER FINISH YOUR CHANGES DIGIT [-1] TO STOP");
    }

    @Override
    public void displaySelectCardMessage() {
        System.out.println("~~ SELECT A VALID CARD ~~\n");
    }

    @Override
    public void displayDefenseModeActions() {
        System.out.println("[1] - PASS TURN\n[2] - DEFEND");
    }

    @Override
    public void displayCard(int index, Card card) {
        System.out.printf(" [" + index + "]\n" + card + " ");
    }

    @Override
    public void displayEvokeAreaActions() {
        System.out.println("YOU HAVE REACHED THE EVOKED UNITS LIMIT\n[1] - DISCARD A CARD\n[2] - CANCEL");
    }

    @Override
    public PlayerActions chooseAttackerAction() {
        int option = chooseOption();

        if (option < 1 || option > 4) {
            throw new OptionsException("Invalid input, try again");
        }

        if (option == 1) {
            return PlayerActions.CHOOSE_HAND_CARD;
        } else if (option == 2) {
            return PlayerActions.PASS_TURN;
        } else if (option == 3) {
            return PlayerActions.ATTACK;
        } else {
            return PlayerActions.FINISH_WITHOUT_ATTACK;
        }
    }

    @Override
    public PlayerActions chooseDefenserAction() {
        int option = chooseOption();

        if (option < 1 || option > 2) {
            throw new OptionsException("Invalid input, try again");
        }

        return (option == 1) ? PlayerActions.CHOOSE_HAND_CARD : PlayerActions.PASS_TURN;
    }

    @Override
    public PlayerActions chooseDefenseModeActions() {
        int option = chooseOption();

        if (option < 1 || option > 2) {
            throw new OptionsException("Invalid input, try again");
        }

        return (option == 1) ? PlayerActions.PASS_TURN : PlayerActions.DEFEND;
    }

    @Override
    public PlayerActions chooseEvokeAreaActions() {
        int option = chooseOption();

        if (option < 1 || option > 2) {
            throw new OptionsException("Invalid input, try again");
        }

        return (option == 1) ? PlayerActions.DISCARD_UNIT : PlayerActions.KEEP_UNIT;
    }

    @Override
    public int chooseCardOption() {
        return chooseOption();
    }

    @Override
    public PlayerActions changeInitialHandOptions() {
        int option = chooseOption();

        if (option < 1 || option > 2) {
            throw new OptionsException("Invalid input, try again");
        }

        return (option == 1) ? PlayerActions.CHANGE_INITIAL_HAND : PlayerActions.KEEP_HAND;
    }

    public boolean cancelOperation(int value) {
        return value == CANCEL_OPERATION;
    }

    private int chooseOption() {
        System.out.println("Type your option: ");
        return scanner.nextInt();
    }
}