package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades;

import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.Champion;

public class LifeUpgrade implements ChampionUpgrade {
    private final int healthPoints;

    public LifeUpgrade(int healthPoints) {
        this.healthPoints = healthPoints;

        if (this.healthPoints <= 0) {
            throw new ChampionUpgradeException("Champions's extra health cannot be negative or 0!");
        }
    }

    @Override
    public void upgradeLevel(Champion champion) {
        champion.increaseHealth(this.healthPoints);
    }
}
