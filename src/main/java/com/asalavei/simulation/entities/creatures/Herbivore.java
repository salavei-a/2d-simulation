package com.asalavei.simulation.entities.creatures;

import com.asalavei.simulation.Coordinates;
import com.asalavei.simulation.WorldMap;
import com.asalavei.simulation.entities.Entities;
import com.asalavei.simulation.entities.environment.Resource;

import java.util.Map;

public abstract class Herbivore extends Creature<Resource> {

    protected Herbivore(Coordinates coordinates, int speed, Entities entityType) {
        super(coordinates, speed, entityType);
    }

    @Override
    protected void interactWithEntity(Map.Entry<Coordinates, Resource> targetNearby, WorldMap map) {
        eat(targetNearby.getKey(), targetNearby.getValue(), map);
    }

    private void eat(Coordinates coordinates, Resource resource, WorldMap map) {
        map.removeEntity(coordinates);
        increaseHP(resource.getHP());
    }

    @Override
    protected Class<Resource> getTargetClass() {
        return Resource.class;
    }
}
