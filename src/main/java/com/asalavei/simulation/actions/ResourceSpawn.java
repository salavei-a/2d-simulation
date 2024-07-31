package com.asalavei.simulation.actions;

import com.asalavei.simulation.WorldMap;
import com.asalavei.simulation.entities.Entities;

import java.util.ArrayList;
import java.util.List;

public class ResourceSpawn extends SpawnAction {
    private static final List<Entities> resources = new ArrayList<>();

    static {
        resources.add(Entities.GRASS);
        resources.add(Entities.FLOWER);
    }

    @Override
    public WorldMap doAction(WorldMap map) {
        for (Entities resource : resources) {
            double currentRate = getCurrentRate(resource.getEntityClass(), map);

            spawnWhileCurrentRateIsLower(currentRate, resource, map);
        }

        return map;
    }
}
