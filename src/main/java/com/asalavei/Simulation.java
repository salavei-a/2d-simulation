package com.asalavei;

import com.asalavei.actions.Action;
import com.asalavei.model.common.WorldMap;
import com.asalavei.view.ConsoleRenderer;
import com.asalavei.view.Renderer;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Simulation {
    private boolean paused = false;
    private boolean running = true;
    private final Logger logger = Logger.getLogger(getClass().getName());
    private WorldMap map;

    public Simulation(WorldMap map) {
        this.map = map;
    }

    public void start() {
        Renderer renderer = new ConsoleRenderer();
        int turnCounter = 0;

        map = initMap(map);
        renderer.render(map, turnCounter);

        while (isActive()) {
            try {
                TimeUnit.SECONDS.sleep(0);

                if (!paused) {
                    map = nextTurn(map);
                    turnCounter++;
                    renderer.render(map, turnCounter);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.info("Thread was interrupted. Shutdown");
                return;
            }
        }

        renderer.printMenu();
    }

    private boolean isActive() {
        return map.isHerbivoresAlive() && running;
    }

    private WorldMap initMap(WorldMap map) {
        List<Action> initActions = ActionFactory.getInitActions();
        return initActions.getFirst().doAction(map);
    }

    public WorldMap nextTurn(WorldMap map) {
        List<Action> turnActions = ActionFactory.getTurnActions();

        for (Action action : turnActions) {
            map = action.doAction(map);
        }

        return map;
    }

    public void togglePause() {
        paused = !paused;
    }

    public void stop() {
        running = false;
    }
}
