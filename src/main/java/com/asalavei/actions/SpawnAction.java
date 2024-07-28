package com.asalavei.actions;

import com.asalavei.CoordinatesFactory;
import com.asalavei.EntityFactory;
import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.Entity;

import java.util.List;
import java.util.Map;

public abstract class SpawnAction extends Action {
    protected void spawnWhileCurrentRateIsLower(double currentRate, Entities entity, WorldMap map) {
        while (currentRate < entity.getSpawnRate()) {
            Coordinates coordinates = CoordinatesFactory.getRandomCoordinates(map.getSize());

            if (map.isPlaceEmpty(coordinates)) {
                map.setEntity(coordinates, EntityFactory.createEntity(entity, coordinates));
                currentRate = getCurrentRate(map.getEntities(), entity.getEntityClass(), map);
            }
        }
    }

    protected double getCurrentRate(Map<Coordinates, Entity> entities, Class<? extends Entity> entityClass, WorldMap map) {
        List<Entity> filteredEntities = entities.values()
                .stream()
                .filter(entityClass::isInstance)
                .toList();

        return filteredEntities.size() / Math.pow(map.getSize(), 2);
    }
}
