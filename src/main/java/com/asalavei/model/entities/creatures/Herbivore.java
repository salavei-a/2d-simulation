package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.environment.Resource;

import java.util.Map;
import java.util.stream.Collectors;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates) {
        super(coordinates, Entities.HERBIVORE);
    }

    @Override
    public void makeMove(WorldMap map) {
        Map<Coordinates, Resource> resourcesNearby = map.getEntitiesNearby(coordinates).entrySet()
                .stream()
                .filter(entry -> entry.getValue() instanceof Resource)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (Resource) entry.getValue()));

        if (!resourcesNearby.isEmpty()) {
            Map.Entry<Coordinates, Resource> resourceToConsume = resourcesNearby.entrySet().iterator().next();
            eat(resourceToConsume.getKey(), resourceToConsume.getValue(), map);
        } else {
            moveToEntity(map);
        }
    }

    private void eat(Coordinates coordinates, Resource resource, WorldMap map) {
        map.removeEntity(coordinates);
        increaseHP(resource.getHP());
    }
}
