package com.unicamp.mc322.enchantedlegends.game.queue;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

public class Combat {
    private final Follower attacker;
    private final Follower defender;

    public Combat(Follower attacker, Follower defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public Follower getAttacker() {
        return attacker;
    }

    public Follower getDefender() {
        return defender;
    }
}
