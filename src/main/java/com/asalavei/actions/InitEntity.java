package com.asalavei.actions;

import com.asalavei.EntityFactory;
import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;

import java.util.Random;

public class InitEntity extends Action {

    @Override
    public WorldMap doAction(WorldMap map) {
        Random random = new Random();
        int sizeMap = map.getSize();

        for (int i = 1; i < sizeMap - (sizeMap / 2); i++) {
            for (int j = 1; j < sizeMap - (sizeMap / 2); j++) {
                Coordinates coordinates = new Coordinates(random.nextInt(sizeMap) + 1, random.nextInt(sizeMap) + 1);
                map.getEntities().put(coordinates, new EntityFactory().createEntity(getRandomEntityName(random), coordinates));
            }
        }

        return map;
    }

    private String getRandomEntityName(Random random) {
        int entity = random.nextInt(4) + 1;

        return switch (entity) {
            case 1 -> "Predator";
            case 2 -> "Herbivore";
            case 3 -> "Rock";
            case 4 -> "Grass";
            default -> throw new IllegalArgumentException("Unknown entity number: " + entity);
        };
    }
}
