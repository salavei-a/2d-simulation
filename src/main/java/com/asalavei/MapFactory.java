package com.asalavei;

import com.asalavei.model.common.Map;

public class MapFactory {
    private final EntityFactory entityFactory = new EntityFactory();

    public Map createMap(int size) {
        return Map.create(size);
    }
}
