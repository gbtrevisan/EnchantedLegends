package com.unicamp.mc322.enchantedlegends.effect;

import com.unicamp.mc322.enchantedlegends.Event;
import com.unicamp.mc322.enchantedlegends.GameState;


public interface Effect {

    public void apply(GameState gameState);

    public boolean applicableOnEvent(Event event);

}
