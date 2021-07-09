package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades;

import com.unicamp.mc322.enchantedlegends.game.card.trait.Trait;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.Champion;
import com.unicamp.mc322.enchantedlegends.game.effect.Effect;

import java.util.StringJoiner;

public class MagicUpgrade implements ChampionUpgrade {
    private Effect effect;
    private Trait trait;

    public MagicUpgrade() {
    }

    public MagicUpgrade(Effect effect) {
        if (effect == null) {
            throw new ChampionUpgradeException("The effect you want to add cannot be null");
        }

        this.effect = effect;
        this.trait = null;
    }

    public MagicUpgrade(Trait trait) {
        if (trait == null) {
            throw new ChampionUpgradeException("The trait you want to add cannot be null");
        }

        this.trait = trait;
        this.effect = null;
    }

    @Override
    public void upgradeLevel(Champion champion) {
        if (this.effect != null) {
            champion.addEffect(this.effect);
        } else {
            champion.addTrait(this.trait);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MagicUpgrade.class.getSimpleName() + "[", "]")
                .add("effect=" + effect)
                .add("trait=" + trait)
                .toString();
    }
}
