package com.unicamp.mc322.enchantedlegends.app.console;

import com.unicamp.mc322.enchantedlegends.app.DeckOption;
import com.unicamp.mc322.enchantedlegends.app.EnchantedLegendsAppIO;
import com.unicamp.mc322.enchantedlegends.app.GameMode;
import com.unicamp.mc322.enchantedlegends.app.MenuOption;
import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.player.Player;
import com.unicamp.mc322.enchantedlegends.game.player.cards.PlayerCards;
import com.unicamp.mc322.enchantedlegends.game.serialization.deck.Deck;
import com.unicamp.mc322.enchantedlegends.game.serialization.deck.Decks;

import java.util.List;
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
        System.out.println("[1] - LOOK DECK\n[2] - UPDATE DECK\n[3] - CREATE NEW DECK");
    }

    @Override
    public void displayGameModes() {
        System.out.println("[1] - MULTIPLAYER LOCAL\n[2] - SINGLE PLAYER LOCAL\n[3] - SPECTATOR");
    }

    @Override
    public void displayDecks() {
        List<String> decks = Decks.getInstance().getAllDeckNames();
        for (String deckName: decks) {
            System.out.println("- " + deckName);
        }
    }

    @Override
    public void displayDeck(String name) {
        Deck deck = Decks.getInstance().getByName(name);
        this.visit(deck);
    }

    private int chooseOption() {
        System.out.print("Type your option: ");
        return scanner.nextInt();
    }

    @Override
    public MenuOption chooseMenuOption() {
        for (; ; ) {
            int option = chooseOption();

            try {
                return MenuOption.values()[option - 1];
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Invalid option! Please, retype.");
            }
        }
    }

    @Override
    public GameMode chooseGameMode() {
        for (; ; ) {
            int option = chooseOption();

            try {
                return GameMode.values()[option - 1];
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Invalid option! Please, retype.");
            }
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
        return scanner.next();
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
        deck.accept(this);
    }
}
