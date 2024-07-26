package com.asalavei;

import com.asalavei.view.ConsoleRenderer;
import com.asalavei.view.Renderer;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final Random random = new Random();

    public static void main(String[] args) {
        Renderer renderer = new ConsoleRenderer();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        Optional<Simulation> simulation = Optional.empty();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        renderer.printControls();

        while (isRunning) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                Command command = Command.fromString(input);

                if (command != null) {
                    switch (command) {
                        case START_NEW_SIMULATION -> {
                            simulation.ifPresent(Simulation::stop);

                            simulation = Optional.of(new Simulation(WorldMapFactory.createMap(10)));
                            executorService.submit(simulation.get()::start);
                        }
                        case PAUSE_RESUME -> simulation.ifPresent(Simulation::togglePause);
                        case QUIT -> {
                            simulation.ifPresent(Simulation::stop);
                            isRunning = false;
                        }
                        default -> throw new IllegalArgumentException("Unknown command: " + command);
                    }
                } else {
                    System.out.println("Invalid command: " + input);
                    renderer.printControls();
                }
            }
        }

        executorService.shutdown();
        scanner.close();
    }
}