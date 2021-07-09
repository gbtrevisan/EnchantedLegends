package com.unicamp.mc322.enchantedlegends.game.effect.types.concrete;

import com.unicamp.mc322.enchantedlegends.game.effect.types.OnActivateEffect;
import com.unicamp.mc322.enchantedlegends.game.event.Event;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;

public class BoostAllUnitsEffect extends OnActivateEffect {

    public BoostAllUnitsEffect() {
    }

    @Override
    public void apply() {

    }

    @Override
    public boolean applicableOnEvent(Event event) {
        return event == Event.ACTIVATION;
    }
}
