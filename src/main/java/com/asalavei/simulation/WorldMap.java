package com.asalavei.simulation;

import com.asalavei.simulation.factory.CoordinatesFactory;
import com.asalavei.simulation.entities.Entity;
import com.asalavei.simulation.entities.creatures.Herbivore;

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
        return getAdjacentCoordinates(coordinates, 1).stream()
                .filter(entities::containsKey)
                .collect(Collectors.toMap(
                        adjacentCoordinates -> adjacentCoordinates,
                        entities::get));
    }

    public List<Coordinates> getAdjacentCoordinates(Coordinates coordinates, int adjacencyRadius) {
        List<Coordinates> adjacentCoordinates = new ArrayList<>();

        for (int i = -adjacencyRadius; i <= adjacencyRadius; i++) {
            for (int j = -adjacencyRadius; j <= adjacencyRadius; j++) {
                if (i == 0 && j == 0) continue;

                int row = coordinates.getRow() + i;
                int column = coordinates.getColumn() + j;

                if (isWithinMapBounds(row, column)) {
                    adjacentCoordinates.add(CoordinatesFactory.createCoordinates(row, column));
                }
            }
        }

        return adjacentCoordinates;
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
