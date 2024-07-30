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
        Queue<ArrayList<Coordinates>> queue = new LinkedList<>();
        Set<Coordinates> explored = new HashSet<>();
        Coordinates start = creature.getCoordinates();
        int speed = creature.getSpeed();

        ArrayList<Coordinates> startPath = new ArrayList<>();
        startPath.add(start);
        queue.add(startPath);
        explored.add(start);

        while (!queue.isEmpty()) {
            ArrayList<Coordinates> currentPath = queue.poll();
            Coordinates current = currentPath.getLast();

            if (isTarget(creature, map.getEntity(current))) {
                return getAvailableCoordinatesForMove(currentPath, speed, map);
            }

            for (Coordinates neighbor : map.getAdjacentCoordinates(current, 1)) {
                if (!explored.contains(neighbor) &&
                    (map.isPlaceEmpty(neighbor) || isTarget(creature, map.getEntity(neighbor)))) {
                    ArrayList<Coordinates> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    queue.add(newPath);
                    explored.add(neighbor);
                }
            }
        }

        return start;
    }

    private static Coordinates getAvailableCoordinatesForMove(ArrayList<Coordinates> path, int speed, WorldMap map) {
        for (int i = Math.min(speed, path.size() - 1); i >= 1; i--) {
            Coordinates available = path.get(i);

            if (map.isPlaceEmpty(available)) {
                return available;
            }
        }
        return path.getFirst();
    }

    private static boolean isTarget(Creature<?> creature, Entity entity) {
        return creature instanceof Predator && entity instanceof Herbivore ||
                creature instanceof Herbivore && entity instanceof Resource;
    }
}
