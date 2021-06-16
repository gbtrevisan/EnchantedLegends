package com.unicamp.mc322.enchantedlegends.game.exception;

public class ChampionCreationException extends FollowerCreationException {
    public ChampionCreationException() {
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
