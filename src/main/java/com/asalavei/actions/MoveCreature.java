package com.asalavei.actions;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entity;
import com.asalavei.model.entities.creatures.Creature;

import java.util.HashMap;
import java.util.Map;

public class MoveCreature extends Action {

    @Override
    public WorldMap doAction(WorldMap map) {
        Map<Coordinates, Entity> entities = map.getEntities();
        Map<Coordinates, Entity> tempEntities = new HashMap<>();

        for (Map.Entry<Coordinates, Entity> entity : entities.entrySet()) {
            if (entity.getValue() instanceof Creature creature) {
                Coordinates coordinates = map.getPlaceToMove(creature);
                creature.setCoordinates(coordinates);

                tempEntities.put(coordinates, creature);
            } else {
                tempEntities.put(entity.getKey(), entity.getValue());
            }
        }

        map.setEntities(tempEntities);

        return map;
    }
}
