package com.unicamp.mc322.enchantedlegends.game.player.io;

import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.player.io.options.PlayerActions;

public interface PlayerIO {

    void displayAttackerActions();

    void displayCard(int index, Card card);

    void displayDefenserActions();

    void displayTip();

    void displayDefenseModeActions();

    void displayChangeInitialHandMessage();

    void displaySelectCardMessage();

    void displayEvokeAreaActions();

    PlayerActions chooseAttackerAction();

    PlayerActions chooseDefenserAction();

    PlayerActions changeInitialHandOptions();

    PlayerActions chooseDefenseModeActions();

    PlayerActions chooseEvokeAreaActions();

    int chooseCardOption();
}
