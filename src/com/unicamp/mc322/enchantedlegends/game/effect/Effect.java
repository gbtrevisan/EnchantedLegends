package com.unicamp.mc322.enchantedlegends.game.effect;

import com.unicamp.mc322.enchantedlegends.game.Event;
import com.unicamp.mc322.enchantedlegends.game.GameState;


public interface Effect {
    public void apply();

    public boolean applicableOnEvent(Event event);
}
