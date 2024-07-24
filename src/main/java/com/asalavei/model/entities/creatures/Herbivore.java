package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.environment.Resource;

import java.util.Map;
import java.util.Optional;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates) {
        super(coordinates, Entities.HERBIVORE);
    }

    @Override
    public void makeMove(WorldMap map) {
        Optional<Map.Entry<Coordinates, Resource>> resourceNearby = map.getEntitiesNearby(coordinates).entrySet()
                .stream()
                .filter(entry -> entry.getValue() instanceof Resource)
                .map(entry -> Map.entry(entry.getKey(), (Resource) entry.getValue()))
                .findFirst();

        resourceNearby.ifPresentOrElse(resourceToConsume -> eat(resourceToConsume.getKey(), resourceToConsume.getValue(), map),
                () -> moveToEntity(map));
    }

    private void eat(Coordinates coordinates, Resource resource, WorldMap map) {
        map.removeEntity(coordinates);
        increaseHP(resource.getHP());
    }
}
