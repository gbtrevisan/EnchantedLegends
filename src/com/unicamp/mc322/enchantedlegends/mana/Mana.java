package com.unicamp.mc322.enchantedlegends.mana;

public class Mana {

    private int roundCount;

    private final static int MAX_MANA = 10;

    private int mana;
    private int spellMana;

    public Mana() {
        mana = 0;
        spellMana = 0;
        roundCount = 0;
    }

    private boolean insufficient(int amount) {
        return mana - amount < 0;
    }

    private void startRound() {
        if (roundCount >= 10) {
            mana = 10;
        } else {
            mana = mana + roundCount;
        }
        roundCount++;
    }

    public void finishRound() {
        spellMana = mana % 3;
        mana = 0;
        startRound();
    }

    public void spend(int amount) throws InsufficientManaException {
        if (insufficient(amount)) {
            throw new InsufficientManaException();
        }
        mana = mana - amount;
    }

    public void spendToSpell(int amount) throws InsufficientManaException {
        if (insufficient(amount - spellMana)) {
            throw new InsufficientManaException();
        } else if (amount < spellMana) {
            spellMana = spellMana - amount;
        } else {
            mana = mana + (spellMana - amount);
        }
    }

}
