package com.asalavei;

import com.asalavei.actions.Action;
import com.asalavei.actions.InitEntity;
import com.asalavei.model.common.WorldMap;
import com.asalavei.view.MapConsoleRenderer;
import com.asalavei.view.Renderer;

public class Simulation {
    private WorldMap map;
    private int turnCounter;
    private Action action;
    private final Renderer renderer = new MapConsoleRenderer();

    public Simulation(WorldMap map) {
        this.map = map;
    }

    public void start() {
        int end = 0;
        map = initActions(map);

        while (end != 1) {
            renderer.render(map);
            end++;
        }
    }

    private WorldMap initActions(WorldMap map) {
        return new InitEntity().doAction(map);
    }

    public void nextTurn() {
        //TODO
    }

    public void pause() {
        //TODO
    }
}
