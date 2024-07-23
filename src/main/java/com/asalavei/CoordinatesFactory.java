package com.asalavei;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;

import java.util.HashMap;
import java.util.Map;

import static com.asalavei.Main.RANDOM;

public class CoordinatesFactory {
    private static Map<String, Coordinates> pool = new HashMap<>();

    private CoordinatesFactory() {
    }

    public static Coordinates createCoordinates(Integer row, Integer column) {
        String key = generateKey(row, column);
        Coordinates coordinates = pool.get(key);

        if (coordinates == null) {
            coordinates = Coordinates.create(row, column);
            pool.put(key, coordinates);
        }

        return coordinates;
    }

    private static String generateKey(Integer row, Integer column) {
        return row + "," + column;
    }

    public static Coordinates getRandomCoordinates(WorldMap map) {
        return createCoordinates(RANDOM.nextInt(map.getSize()) + 1, RANDOM.nextInt(map.getSize()) + 1);
    }
}
