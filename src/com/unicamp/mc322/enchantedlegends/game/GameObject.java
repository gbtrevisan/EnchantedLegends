package com.unicamp.mc322.enchantedlegends.game;

public interface GameObject {

    void accept(GameObjectVisitor visitor);
}
