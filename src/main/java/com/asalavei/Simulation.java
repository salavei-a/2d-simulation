package com.asalavei;

import com.asalavei.actions.Action;
import com.asalavei.model.common.Map;
import com.asalavei.view.MapConsoleRenderer;
import com.asalavei.view.Renderer;

public class Simulation {
    private Map map;
    private int turnCounter;
    private Action action;
    private final Renderer renderer = new MapConsoleRenderer();

    public Simulation(Map map) {
        this.map = map;
    }

    public void start() {
        int end = 0;

        while (end != 1 ) {
            renderer.render(map);

            end++;
        }
    }

    public void nextTurn() {
        //TODO
    }

    public void pause() {
        //TODO
    }
}
