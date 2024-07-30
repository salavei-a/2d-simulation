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

    public static Coordinates getPlaceToMove(Creature<?> creature, WorldMap map) {
        Queue<List<Coordinates>> queue = new LinkedList<>();
        Set<Coordinates> explored = new HashSet<>();
        Coordinates start = creature.getCoordinates();
        int speed = creature.getSpeed();

        addToPath(new LinkedList<>(), start, queue, explored);

        while (!queue.isEmpty()) {
            List<Coordinates> currentPath = queue.poll();
            Coordinates current = currentPath.getLast();

            if (isTarget(creature, map.getEntity(current))) {
                return findAvailableMoveCoordinate(currentPath, speed, map);
            }

            for (Coordinates neighbor : map.getAdjacentCoordinates(current, 1)) {
                if (!explored.contains(neighbor) &&
                    (map.isPlaceEmpty(neighbor) || isTarget(creature, map.getEntity(neighbor)))) {
                        addToPath(new LinkedList<>(currentPath), neighbor, queue, explored);
                }
            }
        }

        return start;
    }

    private static void addToPath(LinkedList<Coordinates> path, Coordinates neighbor, Queue<List<Coordinates>> queue, Set<Coordinates> explored) {
        path.add(neighbor);
        queue.add(path);
        explored.add(neighbor);
    }

    private static boolean isTarget(Creature<?> creature, Entity entity) {
        return creature instanceof Predator && entity instanceof Herbivore ||
                creature instanceof Herbivore && entity instanceof Resource;
    }

    private static Coordinates findAvailableMoveCoordinate(List<Coordinates> path, int speed, WorldMap map) {
        for (int i = Math.min(speed, path.size() - 1); i >= 1; i--) {
            Coordinates available = path.get(i);

            if (map.isPlaceEmpty(available)) {
                return available;
            }
        }
        return path.getFirst();
    }
}
