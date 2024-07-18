package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;

public class Predator extends Creature {
    private int attackDamage;

    public Predator(Coordinates coordinates) {
        super(coordinates);
        this.attackDamage = 5;
    }

    @Override
    public void makeMove(WorldMap map) {
        Coordinates coordinates = map.getPlaceToMove(this);

        map.removeEntity(this.getCoordinates());
        map.setEntity(coordinates, this);
        this.setCoordinates(coordinates);
    }

    public void attack() {
        // TODO
    }
}
