package com.unicamp.mc322.enchantedlegends.game.card.effect.concrete;

import com.unicamp.mc322.enchantedlegends.game.card.effect.Effect;
import com.unicamp.mc322.enchantedlegends.game.card.event.CardEvent;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;

public class AnnulEnemyDamageEffect extends Effect {

    public AnnulEnemyDamageEffect() {
        super(CardEvent.ACTIVATE);
    }

    @Override
    protected void apply() {
        GameState.getInstance().getSelf().annulEnemyDamage(GameState.getInstance().getEnemy());
    }

}
