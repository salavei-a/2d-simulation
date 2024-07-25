package com.asalavei;

import com.asalavei.actions.Action;
import com.asalavei.model.common.WorldMap;
import com.asalavei.view.ConsoleRenderer;
import com.asalavei.view.Renderer;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Simulation {
    private volatile boolean isPaused = false;
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

        startKeyListenerForPause();

        while (isSimulationActive()) {
            synchronized (this) {
                while (isPaused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        logger.info("Thread was interrupted during pause. Shutdown");
                        return;
                    }
                }
            }

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

    public void startKeyListenerForPause() {
        new Thread(this::listenForKeyPress).start();
    }

    private void listenForKeyPress() {
        Scanner scanner = new Scanner(System.in);

        while (isSimulationActive()) {
            String line = scanner.nextLine();
            if ("p".equalsIgnoreCase(line)) {
                togglePause();
            }
        }

        scanner.close();
    }

    private void togglePause() {
        synchronized (this) {
            isPaused = !isPaused;
            if (!isPaused) {
                notifyAll();
            }
        }
    }
}
