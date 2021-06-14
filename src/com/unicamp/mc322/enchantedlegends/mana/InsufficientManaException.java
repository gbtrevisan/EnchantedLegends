package com.unicamp.mc322.enchantedlegends.mana;

public class InsufficientManaException extends Exception {

    public InsufficientManaException() {
        super();
    }

    public InsufficientManaException(String message) {
        super(message);
    }

    public InsufficientManaException(Throwable throwable) {
        super(throwable);
    }

    public InsufficientManaException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
