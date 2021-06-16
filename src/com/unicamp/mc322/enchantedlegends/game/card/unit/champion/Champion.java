package com.unicamp.mc322.enchantedlegends.game.card.unit.champion;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.levelup.LevelUp;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.levelup.LevelUpType;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.types.ChampionUpgrade;
import com.unicamp.mc322.enchantedlegends.game.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.exception.ChampionCreationException;

import java.util.List;

public class Champion extends Follower {
    private final LevelUp levelUp;
    private final List<ChampionUpgrade> championUpgrades;

    public Champion(String name, int cost, int damage, int health, LevelUp levelUp, List<ChampionUpgrade> championUpgrades, Effect... effects) {
        super(name, cost, damage, health, effects);
        this.levelUp = levelUp;

        if (championUpgrades.isEmpty()) {
            throw new ChampionCreationException("There must be at least one upgrade!");
        }

        this.championUpgrades = championUpgrades;
    }

    public void addEffect(Effect effectAdd) {
        this.effects.add(effectAdd);
    }

    @Override
    public void combat(Follower enemy) {
        super.combat(enemy);

        if (this.levelUp.shouldLevelUp(LevelUpType.ATTACK) || (this.levelUp.shouldLevelUp(LevelUpType.KILL) && enemy.isDead())) {
            checkLevelUp();
        }
        else if (this.levelUp.shouldLevelUp(LevelUpType.CAUSE_DAMAGE)) {
            checkLevelUp(this.damage);
        }
    }

    @Override
    public void increaseDamage(int amount) {
        super.increaseDamage(amount);

        if (this.levelUp.shouldLevelUp(LevelUpType.IMPROVE_DAMAGE)) {
            checkLevelUp(amount);
        }
    }

    private void canUpgrade() {
        if (this.levelUp.upgradeChampion()) {
            upgradeLevel();
        }
    }

    private void checkLevelUp() {
        this.levelUp.changeLevelUpPoints();
        canUpgrade();
    }

    private void checkLevelUp(int points) {
        this.levelUp.changeLevelUpPoints(points);
        canUpgrade();
    }

    private void upgradeLevel() {
        for (ChampionUpgrade championUpgrade : this.championUpgrades) {
            championUpgrade.upgradeLevel(this);
        }
    }
}