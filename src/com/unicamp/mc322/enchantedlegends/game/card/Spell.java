package com.unicamp.mc322.enchantedlegends.game.card;

import com.unicamp.mc322.enchantedlegends.game.effect.types.OnActivateEffect;

public class Spell extends Card {

    public Spell() {
    }

    public Spell(String name, int cost, OnActivateEffect... effects) {
        super(name, cost, effects);
    }
}
