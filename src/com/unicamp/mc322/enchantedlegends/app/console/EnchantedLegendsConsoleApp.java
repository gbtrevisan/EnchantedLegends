package com.unicamp.mc322.enchantedlegends.app.console;

import com.unicamp.mc322.enchantedlegends.app.*;
import com.unicamp.mc322.enchantedlegends.game.ConsoleGameEngine;
import com.unicamp.mc322.enchantedlegends.game.GameEngine;
import com.unicamp.mc322.enchantedlegends.game.player.Player;

public class EnchantedLegendsConsoleApp implements EnchantedLegendsApp {

    private final EnchantedLegendsAppConsoleIO io = new EnchantedLegendsAppConsoleIO();

    @Override
    public void start() {
        io.displayWelcomeMessage();
        io.displayMenu();
        MenuOption menuOption;

        do {
           menuOption = io.chooseMenuOption();

           if (menuOption == MenuOption.PLAY) {
               io.displayGameModes();
               runGame(io.chooseGameMode());
           } else if (menuOption == MenuOption.DECKS) {
               io.displayDecks();
               io.displayDeckMenu();
               DeckOption deckOption = io.chooseDeckOption();

               if (deckOption == DeckOption.CREATE_NEW_DECK) {
                   createNewDeck();
               } else if (deckOption == DeckOption.LOOK_DECK) {
                   io.displayDeck(io.readInfo("Select deck: "));
               } else if (deckOption == DeckOption.UPDATE_DECK) {
                   updateDeck();
               }
           }
        } while (menuOption != MenuOption.EXIT);
    }

    private void runGame(GameMode gameMode) {
        GameEngine game;

        if (gameMode == GameMode.MULTIPLAYER_LOCAL) {
            game = new ConsoleGameEngine(buildHumanPlayer(), buildHumanPlayer());
        } else if (gameMode == GameMode.SINGLE_PLAYER_LOCAL) {
            game = new ConsoleGameEngine(buildHumanPlayer(), buildBotPlayer());
        } else {
            game = new ConsoleGameEngine(buildBotPlayer(), buildBotPlayer());
        }

        game.run();
    }

    private Player buildBotPlayer() {
        return null;
    }

    private Player buildHumanPlayer() {
        return null;
    }

    private void createNewDeck() {
        
    }

    private void updateDeck() {

    }
}
