package com.asalavei.actions;

import com.asalavei.CoordinatesFactory;
import com.asalavei.EntityFactory;
import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;

public class InitAction extends Action {

    @Override
    public WorldMap doAction(WorldMap map) {
        int mapSize = map.getSize();
        int quantityController = mapSize * 3;

        for (int i = 1; i <= quantityController; i++) {
            Coordinates coordinates = CoordinatesFactory.getRandomCoordinates(mapSize);
            map.setEntity(coordinates, EntityFactory.createEntity(Entities.getRandomEntityType(), coordinates));
        }

        return map;
    }
}
