package com.asalavei.actions;

import com.asalavei.EntityFactory;
import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;

import java.util.Random;

public class InitEntity extends Action {

    @Override
    public WorldMap doAction(WorldMap map) {
        Random random = new Random();
        int quantityController = map.getSize() * 3;

        for (int i = 1; i <= quantityController; i++) {
            Coordinates coordinates = new Coordinates(random.nextInt(map.getSize()) + 1, random.nextInt(map.getSize()) + 1);
            map.getEntities().put(coordinates, new EntityFactory().createEntity(getRandomEntityName(random), coordinates));
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
