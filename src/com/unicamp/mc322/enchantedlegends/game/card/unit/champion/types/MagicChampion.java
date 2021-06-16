package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.types;

import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.Champion;
import com.unicamp.mc322.enchantedlegends.game.effect.Effect;


public class MagicChampion extends ChampionUpgrade {
    private final Effect extraEffect;
    //private final Tracos extraTracos;

    public MagicChampion(Effect extraEffect) {
        //if (extraEffect == null &&traco == null { exception}

        this.extraEffect = extraEffect;
    }

    @Override
    public void upgradeLevel(Champion champion) {
        if (this.extraEffect != null) {
            champion.addEffect(this.extraEffect);
        }
    }
}
