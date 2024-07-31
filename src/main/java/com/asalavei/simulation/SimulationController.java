package com.asalavei.simulation;

import com.asalavei.simulation.factory.WorldMapFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationController {
    private final Renderer renderer = new ConsoleRenderer();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Simulation simulation = new NullSimulation();
    private boolean isRunning = true;

    public void handleCommand(String input) {
        Command command = Command.fromString(input);

        switch (command) {
            case START_NEW_SIMULATION -> {
                simulation.stop();
                simulation = new Simulation(WorldMapFactory.createMap(20));
                executorService.submit(simulation::start);
            }
            case PAUSE_RESUME -> {
                if (simulation instanceof NullSimulation) renderer.printActionUnavailable();

                simulation.togglePause();
            }
            case QUIT -> {
                simulation.stop();
                isRunning = false;
            }
            default -> renderer.printInvalidCommand(input);
        }
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
