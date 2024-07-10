package com.asalavei.model.common;

import com.asalavei.model.entities.Entity;
import com.asalavei.model.entities.creatures.Predator;

import java.util.HashMap;

public class Map {
    private int size;
    private HashMap<Coordinates, Entity> entities = new HashMap<>();

    public Map(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        Coordinates coordinatesPredator1 = new Coordinates(1, 5);
        Coordinates coordinatesPredator2 = new Coordinates(5, 2);
        Coordinates coordinatesPredator3 = new Coordinates(7, 8);

        entities.put(coordinatesPredator1, new Predator());
        entities.put(coordinatesPredator2, new Predator());
        entities.put(coordinatesPredator3, new Predator());

        return !entities.containsKey(coordinates);
    }
}
