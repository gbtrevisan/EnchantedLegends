package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades;

import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.Champion;

import java.util.StringJoiner;

public class HealthUpgrade implements ChampionUpgrade {
    private int healthPoints;

    public HealthUpgrade() {
    }

    public HealthUpgrade(int healthPoints) {
        this.healthPoints = healthPoints;

        if (this.healthPoints <= 0) {
            throw new ChampionUpgradeException("Champions's extra health cannot be negative or 0!");
        }
    }

    @Override
    public void upgradeLevel(Champion champion) {
        champion.increaseHealth(this.healthPoints);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", HealthUpgrade.class.getSimpleName() + "[", "]")
                .add("healthPoints=" + healthPoints)
                .toString();
    }
}
