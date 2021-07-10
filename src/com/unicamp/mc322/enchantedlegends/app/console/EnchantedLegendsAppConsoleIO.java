package com.unicamp.mc322.enchantedlegends.app.console;

import com.unicamp.mc322.enchantedlegends.app.DeckOption;
import com.unicamp.mc322.enchantedlegends.app.EnchantedLegendsAppIO;
import com.unicamp.mc322.enchantedlegends.app.GameMode;
import com.unicamp.mc322.enchantedlegends.app.MenuOption;
import com.unicamp.mc322.enchantedlegends.game.deck.data.Decks;

import java.util.Scanner;

public class EnchantedLegendsAppConsoleIO implements EnchantedLegendsAppIO {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayWelcomeMessage() {
        System.out.println(">> Welcome to EnchantedLegends! <<");
    }

    @Override
    public void displayMenu() {
        System.out.println("[1] - PLAY\n[2] - DECKS\n[3] - EXIT");
    }

    @Override
    public void displayDeckMenu() {
        System.out.println("[1] - LOOK DECKS\n[2] - UPDATE DECK\n[3] - CREATE NEW DECK");
    }

    @Override
    public void displayGameModes() {
        System.out.println("[1] - MULTIPLAYER LOCAL\n[2] - SINGLE PLAYER LOCAL\n[3] - SPECTATOR");
    }

    @Override
    public void displayDecks() {
        Decks.getInstance().showAllDeckNames();
    }

    @Override
    public void displayDeck(String name) {

    }

    private int chooseOption() {
        System.out.println("Type your option: ");
        return scanner.nextInt();
    }

    @Override
    public MenuOption chooseMenuOption() {
        int option = chooseOption();

        if (option == 1) {
            return MenuOption.PLAY;
        } else if (option == 2) {
            return MenuOption.DECKS;
        } else {
            return MenuOption.EXIT;
        }
    }

    @Override
    public GameMode chooseGameMode() {
        int option = chooseOption();

        if (option == 1) {
            return GameMode.MULTIPLAYER_LOCAL;
        } else if (option == 2) {
            return GameMode.SINGLE_PLAYER_LOCAL;
        } else {
            return GameMode.SPECTATOR;
        }
    }

    @Override
    public DeckOption chooseDeckOption() {
        int option = chooseOption();

        if (option == 1) {
            return DeckOption.LOOK_DECK;
        } else if (option == 2) {
            return DeckOption.UPDATE_DECK;
        } else {
            return DeckOption.CREATE_NEW_DECK;
        }
    }

    String readInfo(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
