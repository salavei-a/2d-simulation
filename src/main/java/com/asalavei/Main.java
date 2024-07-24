package com.asalavei;

import java.util.Random;

public class Main {
    public static final Random random = new Random();

    public static void main(String[] args) {
        Simulation simulation = new Simulation(WorldMapFactory.createMap(10));

        simulation.start();
    }
}