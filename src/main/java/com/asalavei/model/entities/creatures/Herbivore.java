package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.environment.Resource;

import java.util.Map;

public class Herbivore extends Creature<Resource> {

    public Herbivore(Coordinates coordinates) {
        super(coordinates, Entities.HERBIVORE);
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
