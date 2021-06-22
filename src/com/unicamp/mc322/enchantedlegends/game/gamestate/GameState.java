package com.unicamp.mc322.enchantedlegends.game.gamestate;

import com.unicamp.mc322.enchantedlegends.game.arena.Arena;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.player.Player;
import com.unicamp.mc322.enchantedlegends.game.queue.CombatQueue;
import com.unicamp.mc322.enchantedlegends.game.util.Pair;

public class GameState {
    private static GameState instance;
    private final Arena arena;
    private final CombatQueue combats = new CombatQueue();
    private Player self, enemy;

    private GameState() {
        this.arena = new Arena();
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }

        return instance;
    }

    public void setPlayer1(Player player) {
        this.self = player;
    }

    public void setPlayer2(Player player) {
        this.enemy = player;
    }

    public Player getSelf() {
        return self;
    }

    public Player getEnemy() {
        return enemy;
    }

    public void addCardToAttack(Follower follower) {
        this.arena.addAttacker(follower);
    }

    public void removeCardToAttack(Follower follower) {
        this.arena.removeAttacker(follower);
    }

    public void addCardToDefend(Follower follower) {
        this.arena.addDefender(follower);
    }

    public void removeCardToDefend(Follower follower) {
        this.arena.removeDefender(follower);
    }

    public void setStateToCombat() {
        if (arena.hasNotTheSameAmountForEach()) {
            throw new RuntimeException("Attackers size != defenders size"); // n qro pensar mt nisso agora (n eh o objetivo deste pr)
        }

        for (int i = 0; i < arena.getAttackersSize(); i++) {
            this.combats.add(arena.getPair(i));
        }
    }

    public Follower getCurrentDefender() {
        Pair<Follower, Follower> combatPair = this.combats.peek();

        if (combatPair == null) {
            throw new RuntimeException("Unexpected error: Combat is null!");
        }

        return combatPair.getRight();
    }

    public void nextTurn() {
        Player aux = enemy;
        enemy = self;
        self = aux;
    }

    public void nextRound() {
        this.arena.clear();
    }
}
