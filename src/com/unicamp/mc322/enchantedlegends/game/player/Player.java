package com.unicamp.mc322.enchantedlegends.game.player;

import com.unicamp.mc322.enchantedlegends.game.GameObject;
import com.unicamp.mc322.enchantedlegends.game.GameObjectVisitor;
import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.card.mana.Mana;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.player.cards.PlayerCards;

import java.util.Objects;

public abstract class Player implements GameObject {

    private final String name;
    protected final Mana mana;
    protected final Nexus nexus;
    protected PlayerCards playerCards;
    protected boolean onAttack;
    private boolean chosenToAttack;

    public Player(String name) {
        Objects.requireNonNull(name, "Player name must not be null");

        this.name = name;
        this.mana = new Mana();
        this.nexus = new Nexus();
        this.playerCards = new PlayerCards();
        this.onAttack = false;
    }

    public abstract int chooseCard();

    public abstract int choosePositonToEvokeUnit();

    public abstract int chooseHandCard();

    public abstract int chooseEnemyCard(Player enemy);

    public abstract void chooseDeck();

    public void toAttack() {
        onAttack = true;
    }

    public void toDefense() {
        onAttack = false;
    }

    public void receiveInitialHand() {
        this.playerCards.getInitialHand();
    }

    public void evokeUnit(Follower follower) {
        int positionToEvoke = choosePositonToEvokeUnit();

        this.playerCards.evokeUnit(positionToEvoke, follower);
    }

    public void selectHandCard() {
        int handCardIndex = chooseHandCard();
        Card handCard = this.playerCards.getSelectedHandCard(handCardIndex);

        handCard.activate(this.mana);
    }

    public void boostAllUnits(int damage, int health) {
        for (Follower unit : this.playerCards.getEvokedUnits()) {
            improveUnitStatus(unit, damage, health);
        }
    }

    public void gainUnit(String cardName) {
        this.playerCards.gainDeckCard(cardName);
    }

    public void gainRandomCard() {
        this.playerCards.getRandomCard();
    }

    public void restoreUnitHealth(int amount) {
        Follower unit = getUnit();
        unit.healFullHealth();
    }

    public void boostUnit(int damage, int health) {
        Follower unit = getUnit();
        improveUnitStatus(unit, damage, health);
    }

    public void startImmediateCombat(Player enemy) {
        Follower unit = getUnit(), enemyUnit = getEnemyUnit(enemy);
        unit.combat(enemyUnit);
    }

    public void attackEnemyNexus(Player enemy) {
        Follower unit = getUnit();
        unit.attackNexus(enemy.nexus);
    }

    public void attackAllEnemyDefenders(Player enemy) {
        Follower unit = getUnit();

        for (Follower enemyUnit : enemy.playerCards.getEvokedUnits()) {
            unit.combat(enemyUnit);
        }
    }

    public void annulEnemyDamage(Player enemy) {
        Follower enemyUnit = getEnemyUnit(enemy);
        enemyUnit.annulAttack();
    }

    public void gainBarrier() {
        Follower unit = getUnit();
        unit.gainBarrier();
    }

    public void attackEnemyNexus(Player enemy, int damage) {
        enemy.nexus.receiveDamage(damage);
    }

    public PlayerCards getPlayerCards() {
        return this.playerCards;
    }

    private void improveUnitStatus(Follower unit, int damage, int health) {
        unit.increaseDamage(damage);
        unit.increaseHealth(health);
    }

    private Follower getUnit() {
        int selectedUnit = chooseCard();

        return this.playerCards.getSelectedEvokedUnit(selectedUnit);
    }

    private Follower getEnemyUnit(Player enemy) {
        int selectedEnemyUnit = chooseEnemyCard(enemy);

        return enemy.playerCards.getSelectedEvokedUnit(selectedEnemyUnit);
    }

    public boolean isAlive() {
        return !nexus.isDead();
    }

    @Override
    public void accept(GameObjectVisitor gameObjectVisitor) {

    }

    public void restoreMana(int value) {
        mana.restore(value);
    }

    public void startTurn() {

    }

    public boolean chosenToAttack() {
        return chosenToAttack;
    }

    public void attack(Player enemy) {

    }

    private void defend() {

    }
}