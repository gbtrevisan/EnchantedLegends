package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.types;

import com.unicamp.mc322.enchantedlegends.game.card.trait.TraitException;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.Champion;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.ChampionUpgrade;
import com.unicamp.mc322.enchantedlegends.game.effect.Effect;

import java.util.List;

public class AttackToLevelUpChampion extends Champion {
    public AttackToLevelUpChampion(String name, int cost, int damage, int health, int levelUpPoints, List<ChampionUpgrade> championUpgrades, Effect... effects) {
        super(name, cost, damage, health, levelUpPoints, championUpgrades, effects);
    }

    @Override
    public void combat(Follower enemy) throws TraitException {
        super.combat(enemy);
        this.decreaseLevelUpPoints();
    }
}
