package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public void makeMove(WorldMap map) {
        Coordinates coordinates = map.getPlaceToMove(this);

        map.removeEntity(this.getCoordinates());
        map.setEntity(coordinates, this);
        this.setCoordinates(coordinates);
    }
}
