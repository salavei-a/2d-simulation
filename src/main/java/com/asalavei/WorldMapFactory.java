package com.asalavei;

import com.asalavei.model.common.WorldMap;

public class WorldMapFactory {

    private WorldMapFactory() {
    }

    public static WorldMap createMap(int size) {
        return WorldMap.create(size);
    }
}
