package com.unicamp.mc322.enchantedlegends.game.effect;

import com.unicamp.mc322.enchantedlegends.game.event.Event;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;


public interface Effect {

    public void apply(GameState gameState);

    public boolean applicableOnEvent(Event event);

}
