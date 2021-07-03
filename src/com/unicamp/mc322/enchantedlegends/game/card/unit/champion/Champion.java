package com.unicamp.mc322.enchantedlegends.game.card.unit.champion;

import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.ChampionUpgrade;
import com.unicamp.mc322.enchantedlegends.game.card.unit.trait.Trait;

import java.util.List;
import java.util.StringJoiner;

public abstract class Champion extends Follower {
    private int levelUpPoints;
    private boolean alreadyPassedLevel;
    private final List<ChampionUpgrade> championUpgrades;

    public Champion(String name, int cost, int damage, int health, int levelUpPoints, List<ChampionUpgrade> championUpgrades, Trait trait, Effect... effects) {
        super(name, cost, damage, health, trait, effects);

        if (levelUpPoints <= 0) {
            throw new ChampionCreationException("Points to pass a level must be greater than zero!");
        }

        this.levelUpPoints = levelUpPoints;

        if (championUpgrades.isEmpty()) {
            throw new ChampionCreationException("There must be at least one upgrade!");
        }

        this.championUpgrades = championUpgrades;
        this.alreadyPassedLevel = false;
    }

    protected void decreaseLevelUpPoints() {
        this.levelUpPoints = Math.max(this.levelUpPoints - 1, 0);
        this.checkUpgrade();
    }

    protected void decreaseLevelUpPoints(int points) {
        this.levelUpPoints = Math.max(this.levelUpPoints - points, 0);
        this.checkUpgrade();
    }

    private boolean canUpgrade() {
        return this.levelUpPoints == 0 && !alreadyPassedLevel;
    }

    private void checkUpgrade() {
        if (canUpgrade()) {
            this.upgradeLevel();
            this.alreadyPassedLevel = true;
        }
    }

    private void upgradeLevel() {
        this.championUpgrades.forEach(championUpgrade -> championUpgrade.upgradeLevel(this));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Champion.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add(attack.toString())
                .add(defense.toString())
                .add("upgrade=" + alreadyPassedLevel)
                .toString();
    }
}