package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.entities.Entities;

public class Horse extends Herbivore {
    public Horse(Coordinates coordinates) {
        super(coordinates, 2, Entities.HORSE);
    }
}