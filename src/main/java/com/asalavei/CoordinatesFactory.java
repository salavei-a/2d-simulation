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

    public static Coordinates createCoordinates(int row, int column) {
        String key = generateKey(row, column);
        return pool.computeIfAbsent(key, k -> Coordinates.create(row, column));
    }

    private static String generateKey(int row, int column) {
        return row + "," + column;
    }

    public static Coordinates getRandomCoordinates(WorldMap map) {
        return createCoordinates(RANDOM.nextInt(map.getSize()) + 1, RANDOM.nextInt(map.getSize()) + 1);
    }
}
