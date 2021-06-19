package com.unicamp.mc322.enchantedlegends.game.queue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;

public class CombatQueue extends AbstractQueue<Combat> {

    private final LinkedList<Combat> elements;

    public CombatQueue() {
        this.elements = new LinkedList<>();
    }

    @Override
    public Iterator<Combat> iterator() {
        return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean offer(Combat t) {
        if (t == null) return false;
        elements.add(t);
        return true;
    }

    @Override
    public Combat poll() {
        Iterator<Combat> iter = elements.iterator();
        Combat t = iter.next();
        if (t != null) {
            iter.remove();
            return t;
        }
        return null;
    }

    @Override
    public Combat peek() {
        return elements.getFirst();
    }
}
