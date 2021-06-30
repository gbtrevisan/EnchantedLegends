package com.unicamp.mc322.enchantedlegends.game.effect;

import com.unicamp.mc322.enchantedlegends.game.event.Event;


public interface Effect {

    void apply();

    boolean applicableOnEvent(Event event);
}
