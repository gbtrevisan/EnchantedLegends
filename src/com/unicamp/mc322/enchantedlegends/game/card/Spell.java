package com.unicamp.mc322.enchantedlegends.game.card;

import com.unicamp.mc322.enchantedlegends.game.effect.types.OnActivateEffect;

public class Spell extends Card {
    public Spell(int id, String name, int cost, OnActivateEffect... effects) {
        super(id, name, cost, effects);
    }
}
