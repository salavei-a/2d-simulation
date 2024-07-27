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
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Queue<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> explored = new HashSet<>();
        List<Coordinates> movementRange = getAvailableMovementCoordinates(creature, map);

        Coordinates start = creature.getCoordinates();
        queue.add(start);
        cameFrom.put(start, null);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            if (isTarget(creature, map.getEntity(current))) {
                return findPathToTarget(start, current, cameFrom, movementRange, map);
            }

            for (Coordinates neighbor : getAdjacentCoordinates(current, map)) {
                if (!explored.contains(neighbor) && !cameFrom.containsKey(neighbor)) {
                    if (map.isPlaceEmpty(neighbor) || isTarget(creature, map.getEntity(neighbor))) {
                        queue.add(neighbor);
                        cameFrom.put(neighbor, current);
                        explored.add(neighbor);
                    }
                }
            }
        }

        return start;
    }

    private static List<Coordinates> getAvailableMovementCoordinates(Creature creature, WorldMap map) {
        int baseRow = creature.getCoordinates().getRow();
        int baseColumn = creature.getCoordinates().getColumn();
        int speed = creature.getSpeed();

        List<Coordinates> availableCoordinates = new ArrayList<>();

        for (int i = -speed; i <= speed; i++) {
            for (int j = -speed; j <= speed; j++) {
                if (i == 0 && j == 0) continue;

                int newRow = baseRow + i;
                int newColumn = baseColumn + j;

                if (map.isWithinMapBounds(newRow, newColumn)) {
                    availableCoordinates.add(CoordinatesFactory.createCoordinates(newRow, newColumn));
                }
            }
        }

        Collections.shuffle(availableCoordinates);

        return availableCoordinates;
    }

    private static List<Coordinates> getAdjacentCoordinates(Coordinates coordinates, WorldMap map) {
        int baseRow = coordinates.getRow();
        int baseColumn = coordinates.getColumn();

        List<Coordinates> availableCoordinates = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                int newRow = baseRow + i;
                int newColumn = baseColumn + j;

                if (map.isWithinMapBounds(newRow, newColumn)) {
                    availableCoordinates.add(CoordinatesFactory.createCoordinates(newRow, newColumn));
                }
            }
        }

        return availableCoordinates;
    }

    private static boolean isTarget(Creature creature, Entity entity) {
        return creature instanceof Predator && entity instanceof Herbivore ||
                creature instanceof Herbivore && entity instanceof Resource;
    }

    private static Coordinates findPathToTarget(Coordinates start, Coordinates goal, Map<Coordinates, Coordinates> cameFrom, List<Coordinates> movementRange, WorldMap map) {
        LinkedList<Coordinates> path = new LinkedList<>();
        Coordinates current = goal;

        while (current != null && !current.equals(start)) {
            path.addFirst(current);
            current = cameFrom.get(current);
        }

        for (Coordinates step : path) {
            if (movementRange.contains(step) && map.isPlaceEmpty(step)) {
                return step;
            }
        }

        return start;
    }
}
