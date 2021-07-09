package com.unicamp.mc322.enchantedlegends.game.card.effect.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.effect.exception.EffectException;
import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;

public class BoostUnitEffect extends Effect {

    private final int damage;
    private final int health;

    public BoostUnitEffect(int damage, int health) {
        super(CardEvent.ACTIVATE);

        if (damage <= 0 || health <= 0) {
            throw new EffectException("BoostAllUnitsEffect damage and health must be a positive number");
        }

        this.damage = damage;
        this.health = health;
    }

    @Override
    public void apply() {
        GameState.getInstance().getSelf().boostAllUnits(damage, health);
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }
}
