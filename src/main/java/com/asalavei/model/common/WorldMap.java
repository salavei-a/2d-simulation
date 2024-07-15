package com.asalavei.model.common;

import com.asalavei.model.entities.Entity;
import com.asalavei.model.entities.creatures.Creature;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private final int size;
    private Map<Coordinates, Entity> entities = new HashMap<>();

    private WorldMap(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public Map<Coordinates, Entity> getEntities() {
        return entities;
    }

    public static WorldMap create(int size) {
        return new WorldMap(size);
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
