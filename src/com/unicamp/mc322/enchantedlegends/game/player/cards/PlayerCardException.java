package com.unicamp.mc322.enchantedlegends.game.player.cards;

import com.unicamp.mc322.enchantedlegends.game.cards.GameCardException;

public class PlayerCardException extends GameCardException {
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
