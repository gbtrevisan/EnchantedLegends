package com.unicamp.mc322.enchantedlegends.game.arena;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Arena {
    private final List<Follower> attackers;
    private final List<Follower> defenders;

    public Arena() {
        this.attackers = new ArrayList<>();
        this.defenders = new ArrayList<>();
    }

    public void addAttacker(Follower follower) {
        this.attackers.add(follower);
    }

    public void removeAttacker(Follower follower) {
        this.attackers.remove(follower);
    }

    public void addDefender(Follower follower) {
        this.defenders.add(follower);
    }

    public void removeDefender(Follower follower) {
        this.defenders.remove(follower);
    }

    public List<Follower> getAttackers() {
        return attackers;
    }

    public List<Follower> getDefenders() {
        return defenders;
    }

    public void clear() {
        this.attackers.clear();
        this.defenders.clear();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Arena.class.getSimpleName() + "[", "]")
                .add("attackers=" + attackers)
                .add("defenders=" + defenders)
                .toString();
    }
}
