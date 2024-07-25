package com.asalavei;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean active = true;

        Simulation simulation = new Simulation(WorldMapFactory.createMap(10));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(simulation::start);

        while (active) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                switch (input) {
                    case "p":
                        simulation.togglePause();
                        break;

                    case "s":
                        simulation.stop();
                        simulation = new Simulation(WorldMapFactory.createMap(10));
                        executorService.submit(simulation::start);
                        break;

                    case "q":
                        simulation.stop();
                        active = false;
                        break;

                    default:
                        System.out.println("Invalid command: " + input);

                }
            }
        }

        executorService.shutdown();
        scanner.close();
    }
}