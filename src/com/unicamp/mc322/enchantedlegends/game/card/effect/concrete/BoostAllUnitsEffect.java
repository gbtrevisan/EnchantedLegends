package com.unicamp.mc322.enchantedlegends.game.card.effect.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;

public class BoostAllUnitsEffect extends BoostUnitEffect {

    public BoostAllUnitsEffect(int damage, int health) {
        super(CardEvent.ACTIVATE, damage, health);
    }

    @Override
    public void apply() {
        GameState.getInstance().getSelf().boostAllUnits(getDamage(), getHealth());
    }

}
