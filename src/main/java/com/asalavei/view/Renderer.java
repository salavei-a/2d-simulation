package com.asalavei.view;

import com.asalavei.model.common.WorldMap;

public interface Renderer {
    void render(WorldMap map, int turnCounter);
}
