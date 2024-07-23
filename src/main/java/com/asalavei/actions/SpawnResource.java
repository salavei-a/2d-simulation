package com.asalavei.actions;

import com.asalavei.CoordinatesFactory;
import com.asalavei.EntityFactory;
import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;

public class SpawnResource extends Action {

    @Override
    public WorldMap doAction(WorldMap map) {
        Coordinates coordinates = CoordinatesFactory.getRandomCoordinates(map);

        if (map.isPlaceEmpty(coordinates)) {
            map.setEntity(coordinates, new EntityFactory().createEntity(Entities.GRASS, coordinates));
        }

        return map;
    }
}
