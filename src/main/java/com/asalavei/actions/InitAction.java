package com.asalavei.actions;

import com.asalavei.CoordinatesFactory;
import com.asalavei.EntityFactory;
import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;

public class InitAction extends Action {

    @Override
    public WorldMap doAction(WorldMap map) {
        int quantityController = map.getSize() * 3;

        for (int i = 1; i <= quantityController; i++) {
            Coordinates coordinates = CoordinatesFactory.getRandomCoordinates(map);
            map.setEntity(coordinates, new EntityFactory().createEntity(Entities.getRandomEntityType(), coordinates));
        }

        return map;
    }
}
