package com.asalavei;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(new WorldMapFactory().createMap(3));

        simulation.start();
    }
}