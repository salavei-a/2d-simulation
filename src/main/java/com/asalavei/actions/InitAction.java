package com.asalavei.actions;

import com.asalavei.EntityFactory;
import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;

import static com.asalavei.Main.RANDOM;

public class InitAction extends Action {

    @Override
    public WorldMap doAction(WorldMap map) {
        int quantityController = map.getSize() * 3;

        for (int i = 1; i <= quantityController; i++) {
            Coordinates coordinates = Coordinates.getRandomCoordinates(map);
            map.setEntity(coordinates, new EntityFactory().createEntity(getRandomEntityName(), coordinates));
        }

        return map;
    }

    private String getRandomEntityName() {
        int entity = RANDOM.nextInt(4) + 1;

        return switch (entity) {
            case 1 -> "Predator";
            case 2 -> "Herbivore";
            case 3 -> "Rock";
            case 4 -> "Grass";
            default -> throw new IllegalArgumentException("Unknown entity number: " + entity);
        };
    }
}
