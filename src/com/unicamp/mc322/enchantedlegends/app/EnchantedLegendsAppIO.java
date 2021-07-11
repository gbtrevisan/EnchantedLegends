package com.unicamp.mc322.enchantedlegends.app;

import com.unicamp.mc322.enchantedlegends.game.GameObjectVisitor;

public interface EnchantedLegendsAppIO extends GameObjectVisitor {

    void displayWelcomeMessage();

    void displayMenu();

    void displayDeckMenu();

    void displayGameModes();

    void displayDecks();

    void displayDeck(String name);

    MenuOption chooseMenuOption();

    DeckOption chooseDeckOption();

    GameMode chooseGameMode();
}
