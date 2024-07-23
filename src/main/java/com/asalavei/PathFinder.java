package com.asalavei;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.creatures.Creature;

import java.util.*;

import static com.asalavei.Main.RANDOM;

public class PathFinder {

    private PathFinder() {
    }

    public static Coordinates getPlaceToMove(Creature creature, WorldMap map) {
        List<Coordinates> availableCoordinates = getValidMovementCoordinates(creature, map);
        int attempts = availableCoordinates.size();
        Set<Coordinates> checkedCoordinates = new HashSet<>();

        while (checkedCoordinates.size() < attempts) {
            Coordinates coordinates = availableCoordinates.get(RANDOM.nextInt(availableCoordinates.size()));

            if (map.isPlaceEmpty(coordinates)) {
                return coordinates;
            }

            checkedCoordinates.add(coordinates);
        }

        return creature.getCoordinates();
    }

    private static List<Coordinates> getValidMovementCoordinates(Creature creature, WorldMap map) {
        int baseRow = creature.getCoordinates().getRow();
        int baseColumn = creature.getCoordinates().getColumn();
        int speed = creature.getSpeed();

        List<Coordinates> availableCoordinates = new ArrayList<>();

        for (int i = -speed; i <= speed; i++) {
            for (int j = -speed; j <= speed; j++) {
                int newRow = baseRow + i;
                int newColumn = baseColumn + j;

                if (map.isValidCoordinates(newRow, newColumn)) {
                    availableCoordinates.add(CoordinatesFactory.createCoordinates(newRow, newColumn));
                }
            }
        }

        return availableCoordinates;
    }
}
