package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;

public class Predator extends Creature {
    private int attackDamage;

    public Predator(Coordinates coordinates) {
        super(coordinates);
        this.attackDamage = 5;
    }

    public void attack() {
        // TODO
    }

    @Override
    public void makeMove() {
        // TODO
    }
}
