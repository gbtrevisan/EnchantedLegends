package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades;

import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.Champion;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.ChampionCreationException;

public class StrengthUpgrade extends ChampionUpgrade {
    private final int extraDamagePoints;

    public StrengthUpgrade(int extraDamagePoints) {
        this.extraDamagePoints = extraDamagePoints;

        if (this.extraDamagePoints <= 0) {
            throw new ChampionCreationException("Champions's extra damage cannot be negative or 0!");
        }
    }

    @Override
    public void upgradeLevel(Champion champion) {
        champion.increaseDamage(this.extraDamagePoints);
    }
}
