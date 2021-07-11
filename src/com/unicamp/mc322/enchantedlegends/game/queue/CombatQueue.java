package com.unicamp.mc322.enchantedlegends.game.queue;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;
import com.unicamp.mc322.enchantedlegends.game.util.Pair;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;

public class CombatQueue extends AbstractQueue<Pair<Follower, Follower>> {

    private final LinkedList<Pair<Follower, Follower>> elements;

    public CombatQueue() {
        this.elements = new LinkedList<>();
    }

    @Override
    public Iterator<Pair<Follower, Follower>> iterator() {
        return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean offer(Pair<Follower, Follower> t) {
        if (t == null) return false;
        elements.add(t);
        return true;
    }

    @Override
    public Pair<Follower, Follower> poll() {
        Iterator<Pair<Follower, Follower>> iter = elements.iterator();
        Pair<Follower, Follower> t = iter.next();
        if (t != null) {
            iter.remove();
            return t;
        }
        return null;
    }

    @Override
    public Pair<Follower, Follower> peek() {
        return elements.getFirst();
    }
}
