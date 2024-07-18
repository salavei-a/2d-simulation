package com.asalavei;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(new WorldMapFactory().createMap(10));

        simulation.start();
    }
}