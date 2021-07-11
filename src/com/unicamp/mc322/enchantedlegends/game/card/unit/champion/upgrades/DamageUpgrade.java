package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades;

import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.Champion;

import java.util.StringJoiner;

public class DamageUpgrade implements ChampionUpgrade {
    private int damagePoints;

    public DamageUpgrade() {
    }

    public DamageUpgrade(int damagePoints) {
        this.damagePoints = damagePoints;

        if (this.damagePoints <= 0) {
            throw new ChampionUpgradeException("Champions's extra damage cannot be negative or 0!");
        }
    }

    @Override
    public void upgradeLevel(Champion champion) {
        champion.increaseDamage(this.damagePoints);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DamageUpgrade.class.getSimpleName() + "[", "]")
                .add("damagePoints=" + damagePoints)
                .toString();
    }
}
