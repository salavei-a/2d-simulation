package com.asalavei.simulation.entities.creatures;

import com.asalavei.simulation.entities.Entities;
import com.asalavei.simulation.Coordinates;

public class Horse extends Herbivore {
    public Horse(Coordinates coordinates) {
        super(coordinates, 2, Entities.HORSE);
    }
}