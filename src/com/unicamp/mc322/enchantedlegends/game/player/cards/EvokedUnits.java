package com.unicamp.mc322.enchantedlegends.game.player.cards;

import com.unicamp.mc322.enchantedlegends.game.card.unit.Follower;

import java.util.Arrays;
import java.util.List;

public class EvokedUnits {

    private Follower[] evokedUnitsArea;
    private final static int MAX_EVOKED_UNITS = 6;

    EvokedUnits() {
        this.evokedUnitsArea = new Follower[MAX_EVOKED_UNITS];
    }

    Follower getSelectedEvokedUnit(int cardIndex) {
        if (isIndexInvalid(cardIndex) && this.evokedUnitsArea[cardIndex - 1] == null) {
            throw new PlayerCardException("The desired index " + cardIndex + " is invalid");
        }

        return this.evokedUnitsArea[cardIndex];
    }

    void evokeNewUnitAt(int index, Follower unit) {
        if (isIndexInvalid(index) || this.evokedUnitsArea[index - 1] != null) {
            throw new PlayerCardException("Select a valid position to evoke your unit");
        }

        this.evokedUnitsArea[index - 1] = unit;
    }

    List<Follower> getEvokedUnits() {
        return Arrays.asList(this.evokedUnitsArea);
    }

    int getNumberOfEvokedUnits() {
        int count = 0;

        for (int i = 0; i < MAX_EVOKED_UNITS; i++) {
            if (this.evokedUnitsArea[i] != null) {
                count++;
            }
        }

        return count;
    }

    int sizeOfEvokeArea() {
        return MAX_EVOKED_UNITS;
    }

    private boolean isIndexInvalid(int index) {
        return index < 1 || index > MAX_EVOKED_UNITS;
    }
}