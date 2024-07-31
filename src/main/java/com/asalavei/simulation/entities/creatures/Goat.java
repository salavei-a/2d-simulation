package com.asalavei.simulation.entities.creatures;

import com.asalavei.simulation.Coordinates;
import com.asalavei.simulation.entities.Entities;

public class Goat extends Herbivore {
    public Goat(Coordinates coordinates) {
        super(coordinates, 1, Entities.GOAT);
    }
}
