package com.asalavei;

import com.asalavei.actions.Action;
import com.asalavei.model.common.WorldMap;
import com.asalavei.view.ConsoleRenderer;
import com.asalavei.view.Renderer;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Simulation {
    private WorldMap map;
    private int turnCounter;
    private final Map<String, Action> actions;

    private final Logger logger = Logger.getLogger(getClass().getName());

    public Simulation(WorldMap map) {
        this.map = map;
        this.actions = ActionFactory.getAction();
    }

    public void start() {
        Renderer renderer = new ConsoleRenderer();

        map = initMap(map);
        renderer.render(map, turnCounter);

        while (isSimulationActive()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.info("Thread was interrupted. Shutdown");
                break;
            }

            map = nextTurn(map);
            turnCounter++;
            renderer.render(map, turnCounter);
        }
    }

    private boolean isSimulationActive() {
        return map.isHerbivoresAlive();
    }

    private WorldMap initMap(WorldMap map) {
        return actions.get("InitAction").doAction(map);
    }

    public WorldMap nextTurn(WorldMap map) {
        map = actions.get("MoveCreature").doAction(map);
        map = actions.get("SpawnResource").doAction(map);

        return map;
    }

    public void pause() {
        //TODO
    }
}
