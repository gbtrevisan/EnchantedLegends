package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.types;

import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.Champion;
import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades.ChampionUpgrade;
import com.unicamp.mc322.enchantedlegends.game.card.unit.trait.Trait;
import com.unicamp.mc322.enchantedlegends.game.card.unit.trait.exception.TraitException;

import java.util.List;

public class AttackToLevelUpChampion extends Champion {

    public AttackToLevelUpChampion() {
    }

    public AttackToLevelUpChampion(String name, int cost, int damage, int health, int levelUpPoints, List<ChampionUpgrade> upgrades) {
        super(name, cost, damage, health, levelUpPoints, upgrades);
    }

    public AttackToLevelUpChampion(String name, int cost, int damage, int health, Trait trait, int levelUpPoints, List<ChampionUpgrade> upgrades) {
        super(name, cost, damage, health, trait, levelUpPoints, upgrades);
    }

    public AttackToLevelUpChampion(String name, int cost, int damage, int health, List<Effect> effects, int levelUpPoints, List<ChampionUpgrade> upgrades) {
        super(name, cost, damage, health, effects, levelUpPoints, upgrades);
    }

    public AttackToLevelUpChampion(String name, int cost, int damage, int health, Trait trait, List<Effect> effects, int levelUpPoints, List<ChampionUpgrade> upgrades) {
        super(name, cost, damage, health, trait, effects, levelUpPoints, upgrades);
    }

    @Override
    public void combat(Follower enemy) throws TraitException {
        super.combat(enemy);
        this.decreaseLevelUpPoints();
    }
}
