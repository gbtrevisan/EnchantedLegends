package com.unicamp.mc322.enchantedlegends.game.gamestate;

import com.unicamp.mc322.enchantedlegends.game.arena.Arena;
import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.player.Player;
import com.unicamp.mc322.enchantedlegends.game.queue.Combat;
import com.unicamp.mc322.enchantedlegends.game.queue.CombatQueue;

import java.util.List;

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
        List<Follower> attackers = this.arena.getAttackers();
        List<Follower> defenders = this.arena.getDefenders();

        if (defenders.size() != attackers.size()) {
            throw new RuntimeException("Attackers size != defenders size"); // n qro pensar mt nisso agora (n eh o objetivo deste pr)
        }

        for (int i = 0; i < attackers.size(); i++) {
            this.addToCombatQueue(attackers.get(i), defenders.get(i));
        }
    }

    public void addToCombatQueue(Follower attacker, Follower defender) {
        this.combats.add(new Combat(attacker, defender));
    }

    public Follower getCurrentDefender() {
        Combat combat = this.combats.peek();

        if (combat == null) {
            throw new RuntimeException("Unexpected error: Combat is null!");
        }

        return combat.getDefender();
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
