package com.asalavei.actions;

import com.asalavei.CoordinatesFactory;
import com.asalavei.EntityFactory;
import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.Entity;
import com.asalavei.model.entities.environment.Resource;

import java.util.Set;
import java.util.stream.Collectors;

public class SpawnResource extends Action {
    private static final double SPAWN_RATE = 0.2;

    @Override
    public WorldMap doAction(WorldMap map) {
        Set<Entity> resources = map.getEntities().values()
                .stream()
                .filter(Resource.class::isInstance)
                .collect(Collectors.toSet());

        double currentRate = resources.size() / Math.pow(map.getSize(), 2);

        if (currentRate < SPAWN_RATE) {
            spawnResource(map);
        }

        return map;
    }

    private static void spawnResource(WorldMap map) {
        Coordinates coordinates;

        do {
            coordinates = CoordinatesFactory.getRandomCoordinates(map.getSize());
        } while (!map.isPlaceEmpty(coordinates));

        map.setEntity(coordinates, new EntityFactory().createEntity(Entities.GRASS, coordinates));
    }
}
