package com.asalavei.model.common;

import com.asalavei.model.entities.Entity;
import com.asalavei.model.entities.creatures.Creature;

import java.util.HashMap;

public class Map {
    private final int size;
    private HashMap<Coordinates, Entity> entities = new HashMap<>();

    public Map(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        if (entity instanceof Creature creature) {
            creature.setCoordinates(coordinates);
        }

        entities.put(coordinates, entity);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }
}
