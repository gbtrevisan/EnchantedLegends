package com.unicamp.mc322.enchantedlegends.game.player.cards;

public class PlayerCardException extends IllegalArgumentException {
    public PlayerCardException() {
        super();
    }

    public PlayerCardException(String s) {
        super(s);
    }

    public PlayerCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerCardException(Throwable cause) {
        super(cause);
    }
}
