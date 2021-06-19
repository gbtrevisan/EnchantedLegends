package com.unicamp.mc322.enchantedlegends.game.effect;

import com.unicamp.mc322.enchantedlegends.game.event.Event;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;


public interface Effect {

    void apply(GameState gameState);

    boolean applicableOnEvent(Event event);
}
