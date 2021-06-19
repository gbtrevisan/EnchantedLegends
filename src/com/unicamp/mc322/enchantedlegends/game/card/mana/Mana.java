package com.unicamp.mc322.enchantedlegends.game.card.mana;

public class Mana {
    private int mana;
    private int extraMana;

    public int getAmount() {
        return mana;
    }

    public void use(int amount) {
        if (mana < amount) {
            throw new ManaException("Not enough mana!");
        }

        this.mana -= amount;
    }
}
