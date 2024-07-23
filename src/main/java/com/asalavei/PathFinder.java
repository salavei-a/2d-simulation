package com.asalavei;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.creatures.Creature;

import java.util.*;

public class PathFinder {

    private PathFinder() {
    }

    public static Coordinates getPlaceToMove(Creature creature, WorldMap map) {
        List<Coordinates> availableCoordinates = new ArrayList<>(getAvailableMovementCoordinates(creature, map));
        Collections.shuffle(availableCoordinates);

        for (Coordinates coordinates : availableCoordinates) {
            if (map.isPlaceEmpty(coordinates)) {
                return coordinates;
            }
        }

        return creature.getCoordinates();
    }

    private static List<Coordinates> getAvailableMovementCoordinates(Creature creature, WorldMap map) {
        int baseRow = creature.getCoordinates().getRow();
        int baseColumn = creature.getCoordinates().getColumn();
        int speed = creature.getSpeed();

        List<Coordinates> availableCoordinates = new ArrayList<>();

        for (int i = -speed; i <= speed; i++) {
            for (int j = -speed; j <= speed; j++) {
                int newRow = baseRow + i;
                int newColumn = baseColumn + j;

                if (map.isWithinMapBounds(newRow, newColumn)) {
                    availableCoordinates.add(CoordinatesFactory.createCoordinates(newRow, newColumn));
                }
            }
        }

        return availableCoordinates;
    }
}
