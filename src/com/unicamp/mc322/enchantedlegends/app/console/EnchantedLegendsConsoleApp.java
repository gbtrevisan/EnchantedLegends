package com.unicamp.mc322.enchantedlegends.app.console;

import com.unicamp.mc322.enchantedlegends.app.*;
import com.unicamp.mc322.enchantedlegends.game.ConsoleGameEngine;
import com.unicamp.mc322.enchantedlegends.game.GameEngine;
import com.unicamp.mc322.enchantedlegends.game.player.Player;
import com.unicamp.mc322.enchantedlegends.game.player.types.BotPlayer;
import com.unicamp.mc322.enchantedlegends.game.player.types.HumanPlayer;
import com.unicamp.mc322.enchantedlegends.game.serialization.cards.AllCardsLoader;
import com.unicamp.mc322.enchantedlegends.game.serialization.deck.AllDecksLoader;

public class EnchantedLegendsConsoleApp implements EnchantedLegendsApp {

    private final EnchantedLegendsAppConsoleIO io = new EnchantedLegendsAppConsoleIO();

    @Override
    public void start() {
        AllCardsLoader.getInstance().createAllCardsByDefault();
        AllDecksLoader.getInstance().createAllDecksByDefault();
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
                   updateDeck(io.readInfo("Select deck: "));
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
        } else if (gameMode == GameMode.SPECTATOR) {
            game = new ConsoleGameEngine(buildBotPlayer(), buildBotPlayer());
        } else {
            throw new UnsupportedOperationException("GameMode not implemented");
        }

        game.run();
    }

    private Player buildBotPlayer() {
        return new BotPlayer();
    }

    private Player buildHumanPlayer() {
        return new HumanPlayer(io.readInfo("Choose your nick to play: "));
    }

    private void createNewDeck() {
        
    }

    private void updateDeck(String deckName) {

    }
}
