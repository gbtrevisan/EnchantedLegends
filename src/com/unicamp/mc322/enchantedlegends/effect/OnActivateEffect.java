package com.unicamp.mc322.enchantedlegends.effect;

import com.unicamp.mc322.enchantedlegends.Event;

public abstract class OnActivateEffect implements Effect {

    @Override
    public boolean applicableOnEvent(Event event) {
        return event == Event.ACTIVATION;
    }

}
