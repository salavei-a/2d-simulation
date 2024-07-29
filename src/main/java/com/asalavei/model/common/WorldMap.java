package com.asalavei.model.common;

import com.asalavei.CoordinatesFactory;
import com.asalavei.model.entities.Entity;
import com.asalavei.model.entities.creatures.Herbivore;

import java.util.*;
import java.util.stream.Collectors;

public class WorldMap {
    private final int size;
    private final Map<Coordinates, Entity> entities = new HashMap<>();

    private WorldMap(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public <T extends Entity> List<T> getEntitiesByType(Class<T> entityType) {
        return entities.values().stream()
                .filter(entityType::isInstance)
                .map(entityType::cast)
                .toList();
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

    public boolean isWithinMapBounds(int row, int column) {
        return row <= size && row >= 1 && column <= size && column >= 1;
    }

    public boolean isPlaceEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public <T extends Entity> Map<Coordinates, T> getTargetEntitiesNearby(Coordinates coordinates, Class<T> entityType) {
        return getEntitiesNearby(coordinates).entrySet()
                .stream()
                .filter(entry -> entityType.isInstance(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entityType.cast(entry.getValue())));
    }

    public Map<Coordinates, Entity> getEntitiesNearby(Coordinates coordinates) {
        int row = coordinates.getRow();
        int column = coordinates.getColumn();

        Map<Coordinates, Entity> entitiesNearby = new HashMap<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                int newRow = row + i;
                int newColumn = column + j;

                if (isWithinMapBounds(newRow, newColumn)) {
                    Coordinates adjacentCoordinates = CoordinatesFactory.createCoordinates(newRow, newColumn);

                    entitiesNearby.put(adjacentCoordinates, entities.get(adjacentCoordinates));
                }
            }
        }

        return entitiesNearby;
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
