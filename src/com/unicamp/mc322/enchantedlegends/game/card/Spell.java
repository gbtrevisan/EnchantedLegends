package com.unicamp.mc322.enchantedlegends.game.card;

import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;

import java.util.List;

public class Spell extends Card {

    public Spell() {
    }

    public Spell(String name, int cost) {
        this(name, cost, null);
    }

    public Spell(String name, int cost, List<Effect> effects) {
        super(name, cost, effects);

        for (Effect effect : effects) {
            if (!effect.matchEvent(CardEvent.ACTIVATE)) {
                throw new SpellCreationException("Spell must have only ACTIVATE effects");
            }
        }
    }
}
