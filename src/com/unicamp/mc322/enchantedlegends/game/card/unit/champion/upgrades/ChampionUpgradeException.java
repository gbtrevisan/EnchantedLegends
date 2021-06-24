package com.unicamp.mc322.enchantedlegends.game.card.unit.champion.upgrades;

import com.unicamp.mc322.enchantedlegends.game.card.unit.champion.ChampionCreationException;

public class ChampionUpgradeException extends ChampionCreationException {
    public ChampionUpgradeException() {
        super();
    }

    public ChampionUpgradeException(String s) {
        super(s);
    }

    public ChampionUpgradeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChampionUpgradeException(Throwable cause) {
        super(cause);
    }

}
