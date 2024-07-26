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
        boolean active = true;

        Optional<Simulation> simulation = Optional.empty();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        renderer.printControls();

        while (active) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                switch (input) {
                    case "s" -> {
                        simulation.ifPresent(Simulation::stop);

                        simulation = Optional.of(new Simulation(WorldMapFactory.createMap(10)));
                        executorService.submit(simulation.get()::start);
                    }
                    case "p" -> simulation.ifPresent(Simulation::togglePause);
                    case "q" -> {
                        simulation.ifPresent(Simulation::stop);
                        active = false;
                    }
                    default -> {
                        System.out.println("Invalid command: " + input);
                        renderer.printControls();
                    }
                }
            }
        }

        executorService.shutdown();
        scanner.close();
    }
}