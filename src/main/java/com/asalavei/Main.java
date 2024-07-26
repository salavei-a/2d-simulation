package com.asalavei;

import com.asalavei.view.ConsoleRenderer;
import com.asalavei.view.Renderer;

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

        Simulation simulation = null;
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        renderer.printControls();

        while (active) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                switch (input) {
                    case "s":
                        if (simulation != null) {
                            simulation.stop();
                        }
                        simulation = new Simulation(WorldMapFactory.createMap(10));
                        executorService.submit(simulation::start);
                        break;

                    case "p":
                        if (simulation != null) {
                            simulation.togglePause();
                        }
                        break;

                    case "q":
                        if (simulation != null) {
                            simulation.stop();
                        }
                        active = false;
                        break;

                    default:
                        System.out.println("Invalid command: " + input);
                        renderer.printControls();
                }
            }
        }

        executorService.shutdown();
        scanner.close();
    }
}