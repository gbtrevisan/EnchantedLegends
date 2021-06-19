package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades;

import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.Champion;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.ChampionCreationException;

public class LifeUpgrade extends ChampionUpgrade {
    private final int extraHealthPoints;

    public LifeUpgrade(int extraHealthPoints) {
        this.extraHealthPoints = extraHealthPoints;

        if (this.extraHealthPoints <= 0) {
            throw new ChampionCreationException("Champions's extra health cannot be negative or 0!");
        }
    }

    @Override
    public void upgradeLevel(Champion champion) {
        champion.increaseHealth(this.extraHealthPoints);
    }
}
