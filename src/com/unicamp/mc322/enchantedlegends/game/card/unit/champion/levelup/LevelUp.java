package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.levelup;

import com.unicamp.mc322.enchantedlegends.game.exception.ChampionCreationException;

public class LevelUp {
    private final LevelUpType championLevelUpType;
    private int levelUpPoints;
    private boolean alreadyPassed;

    public LevelUp(LevelUpType championLevelUpType, int levelUpPoints) {
        this.championLevelUpType = championLevelUpType;

        if (levelUpPoints <= 0) {
            throw new ChampionCreationException("Points to pass a level must be greater than zero!");
        }

        this.levelUpPoints = levelUpPoints;
        this.alreadyPassed = false;
    }

    public boolean shouldLevelUp(LevelUpType action) {
        return action == this.championLevelUpType;
    }

    public void changeLevelUpPoints() {
        this.levelUpPoints = Math.max(this.levelUpPoints - 1, 0);
    }

    public void changeLevelUpPoints(int points) {
        this.levelUpPoints = Math.max(this.levelUpPoints - points, 0);
    }

    public boolean upgradeChampion() {
        boolean shouldPass = false;

        if (this.levelUpPoints == 0 && !this.alreadyPassed) {
            shouldPass = this.alreadyPassed = true;
        }

        return shouldPass;
    }
}
