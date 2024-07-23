package com.asalavei.model.common;

import com.asalavei.CoordinatesFactory;
import com.asalavei.model.entities.Entity;
import com.asalavei.model.entities.creatures.Herbivore;

import java.util.*;

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

    public void setEntity(Coordinates coordinates, Entity entity) {
        entities.put(coordinates, entity);
    }

    public static WorldMap create(int size) {
        return new WorldMap(size);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public boolean isValidCoordinates(Coordinates coordinates) {
        return coordinates.getRow() <= size && coordinates.getRow() >= 1 &&
                coordinates.getColumn() <= size && coordinates.getColumn() >= 1;
    }


    public boolean isValidCoordinates(int row, int column) {
        return row <= size && row >= 1 && column <= size && column >= 1;
    }

    public boolean isPlaceEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public Map<Coordinates, Entity> getEntitiesNearby(Coordinates coordinates) {
        int row = coordinates.getRow();
        int column = coordinates.getColumn();

        Map<Coordinates, Entity> entitiesNearby = new HashMap<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Coordinates adjacentCoordinates = CoordinatesFactory.createCoordinates(row + i, column + j);

                if (adjacentCoordinates.equals(coordinates)) {
                    continue;
                }

                entitiesNearby.put(adjacentCoordinates, entities.get(adjacentCoordinates));
            }
        }

        return entitiesNearby;
    }

    public boolean isEntitiesNearby(Coordinates coordinates) {
        return !getEntitiesNearby(coordinates).isEmpty();
    }

    public boolean isHerbivoresAlive() {
        for (Entity entity : entities.values()) {
            if (entity instanceof Herbivore) {
                return true;
            }
        }

        return false;
    }
}
