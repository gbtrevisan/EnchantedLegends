package com.unicamp.mc322.enchantedlegends.game.card.unit.champion;

import com.unicamp.mc322.enchantedlegends.game.card.trait.Trait;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.ChampionUpgrade;
import com.unicamp.mc322.enchantedlegends.game.effect.Effect;

import java.util.List;
import java.util.StringJoiner;

public abstract class Champion extends Follower {
    private int levelUpPoints;
    private boolean alreadyPassedLevel;
    private List<ChampionUpgrade> upgrades;

    public Champion(){}

    public Champion(String name, int cost, int damage, int health, int levelUpPoints, List<ChampionUpgrade> upgrades, Trait trait, Effect... effects) {
        super(name, cost, damage, health, trait, effects);

        if (levelUpPoints <= 0) {
            throw new ChampionCreationException("Points to pass a level must be greater than zero!");
        }

        this.levelUpPoints = levelUpPoints;

        if (upgrades.isEmpty()) {
            throw new ChampionCreationException("There must be at least one upgrade!");
        }

        this.upgrades = upgrades;
        this.alreadyPassedLevel = false;
    }

    public void addEffect(Effect effect) {
        this.effects.add(effect);
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
        this.upgrades.forEach(championUpgrade -> championUpgrade.upgradeLevel(this));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Champion.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add("levelUpPoints=" + levelUpPoints)
                .add("alreadyPassedLevel=" + alreadyPassedLevel)
                .add("upgrades=" + upgrades)
                .toString();
    }
}