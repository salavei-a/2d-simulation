package com.asalavei.simulation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SimulationController simulationController = new SimulationController();
        Scanner scanner = new Scanner(System.in);

        simulationController.getRenderer().printControls();

        while (simulationController.isRunning()) {
            simulationController.handleCommand(scanner.nextLine());
        }

        simulationController.shutdown();
        scanner.close();
    }
}