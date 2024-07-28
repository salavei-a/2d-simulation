package com.asalavei.actions;

import com.asalavei.CoordinatesFactory;
import com.asalavei.EntityFactory;
import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.Entity;

import java.util.List;
import java.util.Map;

public class InitAction extends Action {

    @Override
    public WorldMap doAction(WorldMap map) {
        for (Entities entity : Entities.values()) {
            if (entity == Entities.NO_ENTITY) continue;

            double currentRate = 0;

            while (currentRate < entity.getSpawnRate()) {
                Coordinates coordinates = CoordinatesFactory.getRandomCoordinates(map.getSize());

                if (map.isPlaceEmpty(coordinates)) {
                    map.setEntity(coordinates, EntityFactory.createEntity(entity, coordinates));
                    currentRate = getCurrentRate(map.getEntities(), entity.getEntityClass(), map);
                }
            }
        }

        return map;
    }

    private static double getCurrentRate(Map<Coordinates, Entity> entities, Class<? extends Entity> entityClass, WorldMap map) {
        List<Entity> entity = entities.values()
                .stream()
                .filter(entityClass::isInstance)
                .toList();

        return entity.size() / Math.pow(map.getSize(), 2);
    }
}
