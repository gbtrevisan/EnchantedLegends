package com.unicamp.mc322.enchantedlegends.game.card;

import com.unicamp.mc322.enchantedlegends.game.Event;
import com.unicamp.mc322.enchantedlegends.game.GameState;
import com.unicamp.mc322.enchantedlegends.game.card.mana.Mana;
import com.unicamp.mc322.enchantedlegends.game.card.mana.exception.InsufficientManaException;
import com.unicamp.mc322.enchantedlegends.game.effect.types.OnActivateEffect;

public class Spell extends Card {
    public Spell(String name, int cost, OnActivateEffect... effects) {
        super(name, cost, effects);
    }

    @Override
    public void activate(Mana mana) throws InsufficientManaException {
        mana.use(cost);
        applyEffects(Event.ACTIVATION);
    }
}
