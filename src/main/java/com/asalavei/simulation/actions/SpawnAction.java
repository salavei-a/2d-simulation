package com.asalavei.simulation.actions;

import com.asalavei.simulation.factory.CoordinatesFactory;
import com.asalavei.simulation.factory.EntityFactory;
import com.asalavei.simulation.Coordinates;
import com.asalavei.simulation.WorldMap;
import com.asalavei.simulation.entities.Entities;
import com.asalavei.simulation.entities.Entity;

public abstract class SpawnAction extends Action {

    protected void spawnWhileCurrentRateIsLower(double currentRate, Entities entity, WorldMap map) {
        while (isCurrentRateLower(currentRate, entity)) {
            Coordinates coordinates = CoordinatesFactory.getRandomCoordinates(map.getSize());

            if (map.isPlaceEmpty(coordinates)) {
                map.setEntity(coordinates, EntityFactory.createEntity(entity, coordinates));
                currentRate = getCurrentRate(entity.getEntityClass(), map);
            }
        }
    }

    private boolean isCurrentRateLower(double currentRate, Entities entity) {
        return currentRate < entity.getSpawnRate();
    }

    protected double getCurrentRate(Class<? extends Entity> entityClass, WorldMap map) {
        return map.getEntitiesByType(entityClass).size() / Math.pow(map.getSize(), 2);
    }
}
