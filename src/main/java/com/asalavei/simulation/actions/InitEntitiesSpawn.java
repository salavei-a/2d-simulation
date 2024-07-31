package com.asalavei.simulation.actions;

import com.asalavei.simulation.WorldMap;
import com.asalavei.simulation.entities.Entities;

public class InitEntitiesSpawn extends SpawnAction {
    @Override
    public void doAction(WorldMap map) {
        for (Entities entity : Entities.values()) {
            if (entity == Entities.NO_ENTITY) continue;

            spawnWhileCurrentRateIsLower(0, entity, map);
        }
    }
}
