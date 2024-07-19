package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.environment.Resource;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public void makeMove(WorldMap map) {
        Set<Coordinates> resourcesNearby = map.getEntitiesNearby(coordinates).entrySet()
                .stream()
                .filter(entry -> entry.getValue() instanceof Resource)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        if (!resourcesNearby.isEmpty()) {
            eat(resourcesNearby, map);
        } else {
            moveToEntity(map);
        }
    }

    private void eat(Set<Coordinates> coordinates, WorldMap map) {
        map.removeEntity(coordinates.iterator().next());
        increaseHP(5);
    }
}
