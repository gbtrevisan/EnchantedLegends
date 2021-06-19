package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.championtypes;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.Champion;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.ChampionUpgrade;
import com.unicamp.mc322.enchantedlegends.game.effect.Effect;

import java.util.List;

public class DamageChampion extends Champion {
    public DamageChampion(String name, int cost, int damage, int health, int levelUpPoints, List<ChampionUpgrade> championUpgrades, Effect... effects) {
        super(name, cost, damage, health, levelUpPoints, championUpgrades, effects);
    }

    @Override
    public void combat(Follower enemy) {
        super.combat(enemy);
        this.decreaseLevelUpPoints(this.damage);
    }
}
