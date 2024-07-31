package com.asalavei.simulation;

import com.asalavei.simulation.factory.WorldMapFactory;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationController {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Scanner scanner = new Scanner(System.in);

    private final Renderer renderer = new ConsoleRenderer();
    private Simulation simulation = new NullSimulation();

    private boolean isRunning = true;

    public void run() {
        renderer.printControls();

        while (isRunning) {
            handleCommand(scanner.nextLine());
        }

        shutdown();
    }

    private void handleCommand(String input) {
        Command command = Command.fromString(input);

        switch (command) {
            case START_NEW_SIMULATION -> {
                simulation.stop();
                simulation = new Simulation(WorldMapFactory.createMap(20));
                executorService.submit(() -> simulation.start(renderer));
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

    private void shutdown() {
        executorService.shutdown();
        scanner.close();
    }
}
