package com.asalavei.simulation.factory;

import com.asalavei.simulation.WorldMap;

public class WorldMapFactory {

    private WorldMapFactory() {
    }

    public static WorldMap createMap(int size) {
        return WorldMap.create(size);
    }
}
