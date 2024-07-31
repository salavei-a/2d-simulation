package com.asalavei.simulation;

import com.asalavei.simulation.actions.Action;
import com.asalavei.simulation.factory.ActionFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Simulation {
    private static final Logger logger = Logger.getLogger(Simulation.class.getName());

    private volatile boolean paused = false;
    private volatile boolean stopped = false;

    private WorldMap map;

    public Simulation(WorldMap map) {
        this.map = ActionFactory.getInitAction().doAction(map);
    }

    public void start() {
        Renderer renderer = new ConsoleRenderer();
        int turnCounter = 0;

        renderer.printStart();
        sleep();
        renderer.render(map, turnCounter);

        while (isRunning()) {
            sleep();

            if (!isPaused()) {
                map = nextTurn(map);
                turnCounter++;
                renderer.render(map, turnCounter);
            }
        }

        if (isStopped()) {
            renderer.printStop();
            sleep();
        } else {
            renderer.printEnd();
            renderer.printControls();
        }
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.info("Thread was interrupted during sleep. Simulation is shutting down");
        }
    }

    public WorldMap nextTurn(WorldMap map) {
        List<Action> turnActions = ActionFactory.getTurnActions();

        for (Action action : turnActions) {
            map = action.doAction(map);
        }

        return map;
    }

    private boolean isRunning() {
        return map.isHerbivoresAlive() && !stopped;
    }

    private boolean isPaused() {
        return paused;
    }

    private boolean isStopped() {
        return stopped;
    }


    public synchronized void togglePause() {
        paused = !paused;
    }

    public synchronized void stop() {
        stopped = true;
    }
}
