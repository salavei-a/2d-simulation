package com.asalavei;

import com.asalavei.model.common.WorldMap;

public class MapFactory {

    public WorldMap createMap(int size) {
        return WorldMap.create(size);
    }
}
