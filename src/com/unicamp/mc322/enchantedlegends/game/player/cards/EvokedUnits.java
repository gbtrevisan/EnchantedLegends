package com.unicamp.mc322.enchantedlegends.game.player.cards;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

import java.util.ArrayList;
import java.util.List;

public class EvokedUnits {

    private List<Follower> evokedUnitsArea;
    private final static int MAX_EVOKED_UNITS = 6;

    EvokedUnits() {
        this.evokedUnitsArea = new ArrayList<>();
    }

    Follower getSelectedEvokedUnit(int cardIndex) {
        if (isIndexInvalid(cardIndex) && this.evokedUnitsArea.get(cardIndex - 1) == null) {
            throw new PlayerCardException("The desired index " + cardIndex + " is invalid");
        }

        return this.evokedUnitsArea.get(cardIndex - 1);
    }

    void evokeNewUnit(Follower unit) {
        if (this.evokedUnitsArea.size() == MAX_EVOKED_UNITS) {
            throw new PlayerCardException("You can only envoke " +  MAX_EVOKED_UNITS + " units");
        }

        this.evokedUnitsArea.add(unit);
    }

    Follower removeUnit(int unitIndex) {
        Follower removedFollower = this.evokedUnitsArea.get(unitIndex);
        this.evokedUnitsArea.remove(unitIndex);

        return removedFollower;
    }

    List<Follower> getEvokedUnits() {
        return this.evokedUnitsArea;
    }

    int getNumberOfEvokedUnits() {
        return this.evokedUnitsArea.size();
    }

    boolean isEvokedPositionFull() {
        return this.evokedUnitsArea.size() == MAX_EVOKED_UNITS;
    }

    private boolean isIndexInvalid(int index) {
        return index < 1 || index > MAX_EVOKED_UNITS;
    }
}