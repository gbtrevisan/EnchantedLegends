package com.unicamp.mc322.enchantedlegends.game.exception;

public class FollowerCreationException extends IllegalArgumentException {
    public FollowerCreationException() {
    }

    public FollowerCreationException(String s) {
        super(s);
    }

    public FollowerCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FollowerCreationException(Throwable cause) {
        super(cause);
    }
}
