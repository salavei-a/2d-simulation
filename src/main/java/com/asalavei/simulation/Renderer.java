package com.asalavei.simulation;

public interface Renderer {
    void render(WorldMap map, int turnCounter);

    void printControls();

    void printStart();

    void printEnd();

    void printStop();

    void printActionUnavailable();

    void printInvalidCommand(String command);
}
