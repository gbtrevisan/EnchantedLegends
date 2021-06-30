package com.unicamp.mc322.enchantedlegends.game.arena;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.util.Pair;

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

    public boolean hasNotTheSameAmountForEach() {
        return attackers.size() != defenders.size();
    }

    public int getAttackersSize() {
        return attackers.size();
    }

    public int getDefendersSize() {
        return defenders.size();
    }

    public Pair<Follower, Follower> getPair(int i) {
        return Pair.of(attackers.get(i), defenders.get(i));
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
