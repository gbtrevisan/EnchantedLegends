package com.unicamp.mc322.enchantedlegends.game.card.trait;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

public class FuryTrait implements Trait {

    private final int damageBuff;
    private final int healthBuff;

    public FuryTrait(int damageBuff, int healthBuff) {
        this.damageBuff = damageBuff;
        this.healthBuff = healthBuff;
    }

    @Override
    public void applyIfApplicable(Follower self, Follower enemy) {
        if (enemy.isDead()) {
            self.buff(damageBuff, healthBuff);
        }
    }
}
