package com.unicamp.mc322.enchantedlegends.game.player;

import com.unicamp.mc322.enchantedlegends.game.GameObject;
import com.unicamp.mc322.enchantedlegends.game.GameObjectVisitor;
import com.unicamp.mc322.enchantedlegends.game.arena.Arena;
import com.unicamp.mc322.enchantedlegends.game.card.Card;
import com.unicamp.mc322.enchantedlegends.game.card.mana.Mana;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.player.cards.PlayerCards;
import com.unicamp.mc322.enchantedlegends.game.player.io.options.PlayerActions;

import java.util.Objects;

public abstract class Player implements GameObject {

    protected final Mana mana;
    protected final Nexus nexus;
    private final String name;
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

    public abstract int chooseEvokeUnit();

    public abstract boolean insertUnitToCombat();

    public abstract int chooseHandCard();

    public abstract int chooseEnemyCard(Player enemy);

    public abstract void changeInitialHand();

    public abstract PlayerActions chooseAction();

    public void startTurn() {
        PlayerActions playerAction = null;

        while (playerTurnContinue(playerAction)) {
            playerAction = chooseAction();
            definePlayerAction(playerAction);
        }
    }

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

    public abstract void discardUnit(int unitToDiscard, Follower follower);

    public void evokeUnit(Follower follower) {
        if (this.playerCards.isEvokedPositionFull()) {
            int unitToDiscard = chooseEvokeUnit();
            discardUnit(unitToDiscard, follower);
        } else {
            this.playerCards.evokeUnit(follower);
        }
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

    private Follower getUnit() {
        int selectedUnit = chooseEvokeUnit();

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
        gameObjectVisitor.visit(this);
    }

    public void restoreMana(int value) {
        mana.restore(value);
    }

    public boolean chosenToAttack() {
        return chosenToAttack;
    }

    public void attack(Player enemy) {
        defineCombatCards();
        defend(enemy, defineCombatCards());
    }

    protected void definePlayerAction(PlayerActions playerAction) {
        if (playerAction == PlayerActions.CHOOSE_HAND_CARD) {
            selectHandCard();
        }
    }

    private Arena defineCombatCards() {
        int numberOfCards = 0;
        Arena arena = new Arena();

        do {
            int unitIndex = chooseEvokeUnit();
            Follower unit = this.playerCards.getSelectedEvokedUnit(unitIndex);
            arena.addAttacker(unit);
        } while (numberOfCards != 0 && insertUnitToCombat());

        return arena;
    }

    private void improveUnitStatus(Follower unit, int damage, int health) {
        unit.increaseDamage(damage);
        unit.increaseHealth(health);
    }

    private void defend(Player enemy, Arena combatArena) {
        while (insertUnitToCombat()) {
            int unitIndex = enemy.chooseEvokeUnit();
            Follower unit = enemy.playerCards.getSelectedEvokedUnit(unitIndex);
            combatArena.addDefender(unit);
        }
    }

    private boolean playerTurnContinue(PlayerActions playerAction) {
        return playerAction != PlayerActions.ATTACK && playerAction != PlayerActions.PASS_TURN &&
                playerAction != PlayerActions.FINISH_WITHOUT_ATTACK;
    }
}