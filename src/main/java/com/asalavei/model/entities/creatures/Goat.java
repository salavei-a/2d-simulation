package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.entities.Entities;

public class Goat extends Herbivore {
    public Goat(Coordinates coordinates) {
        super(coordinates, 1, Entities.GOAT);
    }
}
