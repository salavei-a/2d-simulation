package com.asalavei.view;

import com.asalavei.model.common.WorldMap;

public interface Renderer {
    void render(WorldMap map, int turnCounter);

    void printControls();

    void printStart();

    void printEnd();

    void printInterrupt();

    void printActionUnavailable();

    void printInvalidCommand(String command);
}
