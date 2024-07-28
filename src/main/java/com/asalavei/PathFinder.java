package com.asalavei;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entity;
import com.asalavei.model.entities.creatures.Creature;
import com.asalavei.model.entities.creatures.Herbivore;
import com.asalavei.model.entities.creatures.Predator;
import com.asalavei.model.entities.environment.Resource;

import java.util.*;

public class PathFinder {

    private PathFinder() {
    }

    public static Coordinates getPlaceToMove(Creature creature, WorldMap map) {
        Queue<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> explored = new HashSet<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
        List<Coordinates> availableMovementCoordinates = getAvailableMovementCoordinates(creature, map);

        Coordinates start = creature.getCoordinates();
        queue.add(start);
        cameFrom.put(start, null);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            if (isTarget(creature, map.getEntity(current))) {
                return findPathToTarget(start, current, cameFrom, availableMovementCoordinates);
            }

            for (Coordinates neighbor : getAdjacentCoordinates(current, map, 1)) {
                if (!explored.contains(neighbor) &&
                    !cameFrom.containsKey(neighbor) &&
                    (map.isPlaceEmpty(neighbor) || isTarget(creature, map.getEntity(neighbor)))) {
                        queue.add(neighbor);
                        cameFrom.put(neighbor, current);
                        explored.add(neighbor);
                }
            }
        }

        return start;
    }

    private static List<Coordinates> getAvailableMovementCoordinates(Creature creature, WorldMap map) {
        List<Coordinates> availableMovementCoordinates = new ArrayList<>();

        for (Coordinates coordinates : getAdjacentCoordinates(creature.getCoordinates(), map, creature.getSpeed())) {
            if (map.isPlaceEmpty(coordinates)) {
                availableMovementCoordinates.add(coordinates);
            }
        }
        return availableMovementCoordinates;
    }

    private static List<Coordinates> getAdjacentCoordinates(Coordinates coordinates, WorldMap map, int adjacencyRadius) {
        int baseRow = coordinates.getRow();
        int baseColumn = coordinates.getColumn();

        List<Coordinates> adjacentCoordinates = new ArrayList<>();

        for (int i = -adjacencyRadius; i <= adjacencyRadius; i++) {
            for (int j = -adjacencyRadius; j <= adjacencyRadius; j++) {
                if (i == 0 && j == 0) continue;

                int newRow = baseRow + i;
                int newColumn = baseColumn + j;

                if (map.isWithinMapBounds(newRow, newColumn)) {
                    adjacentCoordinates.add(CoordinatesFactory.createCoordinates(newRow, newColumn));
                }
            }
        }

        return adjacentCoordinates;
    }

    private static boolean isTarget(Creature creature, Entity entity) {
        return creature instanceof Predator && entity instanceof Herbivore ||
                creature instanceof Herbivore && entity instanceof Resource;
    }

    private static Coordinates findPathToTarget(Coordinates start, Coordinates goal, Map<Coordinates, Coordinates> cameFrom, List<Coordinates> availableMovementCoordinates) {
        LinkedList<Coordinates> path = new LinkedList<>();
        Coordinates current = goal;

        while (current != null && !current.equals(start)) {
            path.addFirst(current);
            current = cameFrom.get(current);
        }

        for (Coordinates step : path) {
            if (availableMovementCoordinates.contains(step)) {
                return step;
            }
        }

        return start;
    }
}
