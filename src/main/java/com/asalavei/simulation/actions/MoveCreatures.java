package com.asalavei.simulation.actions;

import com.asalavei.simulation.WorldMap;
import com.asalavei.simulation.entities.creatures.Creature;

public class MoveCreatures extends Action {
    @Override
    public WorldMap doAction(WorldMap map) {
        for (Creature<?> creature : map.getEntitiesByType(Creature.class)) {
            creature.makeMove(map);

            if (creature.isDead()) {
                map.removeEntity(creature.getCoordinates());
            }
        }

        return map;
    }
}
