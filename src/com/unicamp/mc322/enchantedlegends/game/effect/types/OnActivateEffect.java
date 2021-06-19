package com.unicamp.mc322.enchantedlegends.game.effect.types;

import com.unicamp.mc322.enchantedlegends.game.effect.Effect;

public abstract class OnActivateEffect implements Effect {
    @Override
    public boolean applicableOnEvent(Event event) {
        return event == Event.ACTIVATION;
    }
}
