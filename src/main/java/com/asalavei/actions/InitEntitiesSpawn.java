package com.asalavei.actions;

import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;

public class InitEntitiesSpawn extends SpawnAction {
    @Override
    public WorldMap doAction(WorldMap map) {
        for (Entities entity : Entities.values()) {
            if (entity == Entities.NO_ENTITY) continue;

            spawnWhileCurrentRateIsLower(0, entity, map);
        }

        return map;
    }
}
