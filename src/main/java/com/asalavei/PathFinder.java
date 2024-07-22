package com.asalavei;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.creatures.Creature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.asalavei.Main.RANDOM;

public class PathFinder {

    private PathFinder() {
    }

    public static Coordinates getPlaceToMove(Creature creature, WorldMap map) {
        List<Coordinates> availableCoordinates = getAvailableMovePlaces(creature);
        int attempts = availableCoordinates.size();
        Set<Coordinates> checkedCoordinates = new HashSet<>();

        while (checkedCoordinates.size() < attempts) {
            Coordinates coordinates = availableCoordinates.get(RANDOM.nextInt(availableCoordinates.size()));

            if (map.isPlaceEmpty(coordinates) && map.isValidCoordinates(coordinates)) {
                return coordinates;
            }

            checkedCoordinates.add(coordinates);
        }

        return creature.getCoordinates();
    }

    private static List<Coordinates> getAvailableMovePlaces(Creature creature) {
        int row = creature.getCoordinates().getRow();
        int column = creature.getCoordinates().getColumn();

        int speed = creature.getSpeed();

        List<Coordinates> availableCoordinates = new ArrayList<>();

        for (int i = -speed; i <= speed; i++) {
            for (int j = -speed; j <= speed; j++) {

                availableCoordinates.add(new Coordinates(row + i, column + j));
            }
        }

        return availableCoordinates;
    }
}
