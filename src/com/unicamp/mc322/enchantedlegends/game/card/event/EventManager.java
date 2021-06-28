package com.unicamp.mc322.enchantedlegends.game.card.event;

import java.util.ArrayList;

public class EventManager {
    private final ArrayList<EventListener> eventListeners;

    public EventManager() {
        eventListeners = new ArrayList<>();
    }

    public void subscribe(EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    public void unsubscribe(EventListener eventListener) {
        eventListeners.remove(eventListener);
    }

    public void update(CardEvent event) {
        for (EventListener eventListener : eventListeners) {
            eventListener.update(event);
        }
    }
}
