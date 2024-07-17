package com.asalavei.model.common;

import com.asalavei.model.entities.Entity;
import com.asalavei.model.entities.creatures.Creature;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

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

    public void setEntities(Map<Coordinates, Entity> entities) {
        this.entities.clear();
        this.entities.putAll(entities);
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

    public Coordinates getPlaceToMove(Creature creature) {
        int speed = creature.getSpeed();
        Set<Coordinates> checkedCoordinates = new HashSet<>();
        int attempts = 0;

        if (speed == 1) {
            attempts = 9;
        }

        while (checkedCoordinates.size() < attempts) {
            Coordinates coordinates = creature.getRandomAvailablePlace(speed);

            if (isPlaceEmpty(coordinates) && isPlaceAvailableToMove(coordinates)) {
                return coordinates;
            }

            checkedCoordinates.add(coordinates);
        }

        return creature.getCoordinates();
    }

    private boolean isPlaceAvailableToMove(Coordinates coordinates) {
        return coordinates.getRow() <= size && coordinates.getRow() >= 1 &&
                coordinates.getColumn() <= size && coordinates.getColumn() >= 1;
    }

    public boolean isPlaceEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }
}
