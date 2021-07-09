package com.unicamp.mc322.enchantedlegends.game.cards;

public class GameCardException extends IllegalArgumentException {
    public GameCardException() {
        super();
    }

    public GameCardException(String s) {
        super(s);
    }

    public GameCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameCardException(Throwable cause) {
        super(cause);
    }
}
