package com.unicamp.mc322.enchantedlegends.game.effect;

import com.unicamp.mc322.enchantedlegends.game.event.Event;

public abstract class OnActivateEffect implements Effect {

    @Override
    public boolean applicableOnEvent(Event event) {
        return event == Event.ACTIVATION;
    }

}
