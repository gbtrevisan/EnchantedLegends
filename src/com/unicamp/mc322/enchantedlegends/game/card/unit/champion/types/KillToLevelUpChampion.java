package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.types;

import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.unit.trait.Trait;
import com.unicamp.mc322.enchantedlegends.game.card.unit.trait.exception.TraitException;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.Champion;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.ChampionUpgrade;

import java.util.List;

public class KillToLevelUpChampion extends Champion {

    public KillToLevelUpChampion(String name, int cost, int damage, int health, int levelUpPoints, List<ChampionUpgrade> championUpgrades, Trait trait, Effect... effects) {
        super(name, cost, damage, health, levelUpPoints, championUpgrades, trait, effects);
    }

    @Override
    public void combat(Follower enemy) throws TraitException {
        super.combat(enemy);

        if (enemy.isDead()) {
            this.decreaseLevelUpPoints();
        }
    }
}
