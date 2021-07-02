package com.unicamp.mc322.enchantedlegends.game.card.unit.champion;

import com.unicamp.mc322.enchantedlegends.game.card.unit.FollowerCreationException;

public class ChampionCreationException extends FollowerCreationException {
    public ChampionCreationException() {
        super();
    }

    public ChampionCreationException(String s) {
        super(s);
    }

    public ChampionCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChampionCreationException(Throwable cause) {
        super(cause);
    }
}
