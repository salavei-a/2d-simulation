package com.asalavei;

import java.util.Random;

public class Main {
    public static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Simulation simulation = new Simulation(new WorldMapFactory().createMap(10));

        simulation.start();
    }
}