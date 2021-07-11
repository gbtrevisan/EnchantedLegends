package com.unicamp.mc322.enchantedlegends.app;

public interface EnchantedLegendsAppIO {

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
