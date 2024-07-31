package com.asalavei.simulation;

import com.asalavei.simulation.factory.ActionFactory;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Simulation {
    private static final Logger logger = Logger.getLogger(Simulation.class.getName());

    private volatile boolean paused = false;
    private volatile boolean stopped = false;

    private final WorldMap map;

    public Simulation(WorldMap map) {
        this.map = map;
    }

    public void start() {
        Renderer renderer = new ConsoleRenderer();
        int turnCounter = 0;

        renderer.printStart();
        sleep();

        initAction(map);
        renderer.render(map, turnCounter);

        while (isRunning()) {
            sleep();

            if (!isPaused()) {
                nextTurn(map);
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

    private void initAction(WorldMap map) {
        ActionFactory.getInitAction().forEach(action -> action.doAction(map));
    }

    private void nextTurn(WorldMap map) {
        ActionFactory.getTurnActions().forEach(action -> action.doAction(map));
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
