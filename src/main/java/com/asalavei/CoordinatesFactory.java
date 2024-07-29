package com.asalavei;

import com.asalavei.model.common.Coordinates;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CoordinatesFactory {
    private static final Map<String, Coordinates> pool = new HashMap<>();
    private static final Random random = new Random();

    private CoordinatesFactory() {
    }

    public static Coordinates createCoordinates(int row, int column) {
        String key = generateKey(row, column);
        return pool.computeIfAbsent(key, k -> Coordinates.create(row, column));
    }

    private static String generateKey(int row, int column) {
        return row + "," + column;
    }

    public static Coordinates getRandomCoordinates(int mapSize) {
        return createCoordinates(random.nextInt(mapSize) + 1, random.nextInt(mapSize) + 1);
    }
}
