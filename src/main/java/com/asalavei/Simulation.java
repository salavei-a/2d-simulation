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
        this.map = ActionFactory.getInitAction().doAction(map);
    }

    public void start() {
        Renderer renderer = new ConsoleRenderer();
        int turnCounter = 0;

        renderer.printStart();

        while (isActive()) {
            try {
                TimeUnit.SECONDS.sleep(1);

                if (!paused) {
                    map = nextTurn(map);
                    renderer.render(map, turnCounter++);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.info("Thread was interrupted. Shutdown");
                return;
            }
        }

        if (running) {
            renderer.printEnd();
            renderer.printControls();
        } else {
            renderer.printInterrupt();
        }
    }

    private boolean isActive() {
        return map.isHerbivoresAlive() && running;
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
