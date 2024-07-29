package com.asalavei.actions;

import com.asalavei.CoordinatesFactory;
import com.asalavei.EntityFactory;
import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.Entity;

public abstract class SpawnAction extends Action {
    protected void spawnWhileCurrentRateIsLower(double currentRate, Entities entity, WorldMap map) {
        while (currentRate < entity.getSpawnRate()) {
            Coordinates coordinates = CoordinatesFactory.getRandomCoordinates(map.getSize());

            if (map.isPlaceEmpty(coordinates)) {
                map.setEntity(coordinates, EntityFactory.createEntity(entity, coordinates));
                currentRate = getCurrentRate(entity.getEntityClass(), map);
            }
        }
    }

    protected double getCurrentRate(Class<? extends Entity> entityClass, WorldMap map) {
        return map.getEntitiesByType(entityClass).size() / Math.pow(map.getSize(), 2);
    }
}
