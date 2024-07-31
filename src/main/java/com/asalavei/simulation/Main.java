package com.asalavei.simulation;

import com.asalavei.simulation.factory.WorldMapFactory;

import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Renderer renderer = new ConsoleRenderer();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        Optional<Simulation> simulation = Optional.empty();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        renderer.printControls();

        while (isRunning) {
            String input = scanner.nextLine();
            Command command = Command.fromString(input);

            switch (command) {
                case START_NEW_SIMULATION -> {
                    simulation.ifPresent(Simulation::stop);

                    simulation = Optional.of(new Simulation(WorldMapFactory.createMap(10)));
                    executorService.submit(simulation.get()::start);
                }
                case PAUSE_RESUME ->
                        simulation.ifPresentOrElse(Simulation::togglePause, renderer::printActionUnavailable);
                case QUIT -> {
                    simulation.ifPresent(Simulation::stop);
                    isRunning = false;
                }
                default -> renderer.printInvalidCommand(input);
            }
        }

        executorService.shutdown();
        scanner.close();
    }
}