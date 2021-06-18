package com.unicamp.mc322.enchantedlegends.game.card.mana;

import com.unicamp.mc322.enchantedlegends.game.card.mana.exception.IllegalManaRestoreException;
import com.unicamp.mc322.enchantedlegends.game.card.mana.exception.InsufficientManaException;

public class Mana {
    private final static int MAX_MANA = 10;
    private final static int MAX_RELOCATED_MANA = 3;

    private int mana;
    private int extra;

    public Mana() {
        mana = 0;
        extra = 0;
    }

    public void restore(int value) throws IllegalManaRestoreException {
        if (value < 0) {
            throw new IllegalManaRestoreException("Value to restore mana should be a positive number!");
        }

        extra = Math.min(mana, MAX_RELOCATED_MANA);
        mana = Math.min(value, MAX_MANA);
    }

    public void use(int amount) throws InsufficientManaException {
        if (mana < amount) {
            throw new InsufficientManaException("Not enough mana for this amount:" + amount);
        }

        mana -= amount;
    }

    public void use(int amount, boolean useExtra) throws InsufficientManaException {
        if (mana + extra < amount) {
            throw new InsufficientManaException("Not enough mana for this amount:" + amount);
        }

        if (useExtra) {
            try {
                this.use(amount);
            } catch (InsufficientManaException e) {
                extra -= (amount - mana);
                mana = 0;
            }
        } else {
            this.use(amount);
        }
    }
}
