package com.asalavei;

import com.asalavei.actions.Action;
import com.asalavei.model.common.WorldMap;
import com.asalavei.view.ConsoleRenderer;
import com.asalavei.view.Renderer;

import java.util.Map;

public class Simulation {
    private WorldMap map;
    private int turnCounter;
    private final Map<String, Action> actions;

    public Simulation(WorldMap map) {
        this.map = map;
        this.actions = ActionFactory.getAction();
    }

    public void start() {
        Renderer renderer = new ConsoleRenderer();

        map = initMap(map);
        renderer.render(map, turnCounter);

        while (isSimulationActive(turnCounter)) {
            turnCounter++;
            renderer.render(map, turnCounter);
        }
    }

    private boolean isSimulationActive(int turnCounter) {
        return turnCounter != 5;
    }

    private WorldMap initMap(WorldMap map) {
        return actions.get("InitAction").doAction(map);
    }

    public WorldMap nextTurn(WorldMap map) {
        return actions.get("MoveCreature").doAction(map);
    }

    public void pause() {
        //TODO
    }
}
