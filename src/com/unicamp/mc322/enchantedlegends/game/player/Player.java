package com.unicamp.mc322.enchantedlegends.game.player;

import com.unicamp.mc322.enchantedlegends.game.card.mana.Mana;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

public abstract class Player {

    private final String name;
    private final Mana mana;
    private boolean onAttack;

    public Player(String name) {
        this.name = name;
        mana = new Mana();
        onAttack = false;
    }

    public void toAttack() {
        onAttack = true;
    }

    public void toDefense() {
        onAttack = false;
    }

    public void evokeUnit(Follower follower) {

    }

    public void boostAllUnits(int damage, int health) {

    }

    public void boostUnit(int damage, int health) {

    }

    public void gainUnit(String unitName) {

    }

    public void gainRandomCard() {

    }

    public void restoreUnitHealth(int amount) {

    }

    public abstract void chooseCard();

    public abstract void chooseEnemyCard();

    public void startImmediateCombat() {

    }

    public void attackEnemyNexus(Player enemy) {

    }

    public void attackAllEnemyDefenders(Player enemy) {

    }

    public void annulEnemyDamage(Player enemy) {

    }

    public void gainBarrier() {

    }

    public void attackEnemyNexus(int damage) {

    }

}