package com.unicamp.mc322.enchantedlegends.game.effect.types.concrete;

import com.unicamp.mc322.enchantedlegends.game.effect.types.OnActivateEffect;
import com.unicamp.mc322.enchantedlegends.game.event.Event;
import com.unicamp.mc322.enchantedlegends.game.gamestate.GameState;

public class BoostAllUnitsEffect extends OnActivateEffect {
    /*
      Eu editei aqui só pra deixar eu dar o merge. Eu preciso desligar o PC e ir tomar meu leitinho noturno.
      Boa noite ^-^
    */

    public BoostAllUnitsEffect() {
    }

    @Override
    public boolean applicableOnEvent(Event event) {
        return event == Event.ACTIVATION;
    }

    @Override
    public void apply(GameState gameState) {

    }
}
