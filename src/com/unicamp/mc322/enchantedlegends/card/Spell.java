package com.unicamp.mc322.enchantedlegends.card;

import com.unicamp.mc322.enchantedlegends.Event;
import com.unicamp.mc322.enchantedlegends.GameState;
import com.unicamp.mc322.enchantedlegends.mana.InsufficientManaException;
import com.unicamp.mc322.enchantedlegends.mana.Mana;
import com.unicamp.mc322.enchantedlegends.effect.OnActivateEffect;

public class Spell extends Card {

    public Spell(String name, int cost, OnActivateEffect... effects) {
        super(name, cost, effects);
    }

    @Override
    public void activate(Mana mana, GameState gameState) throws InsufficientManaException {
        mana.spendToSpell(cost);
        applyEffects(gameState, Event.ACTIVATION);
    }

}
