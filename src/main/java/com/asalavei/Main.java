package com.asalavei;

import com.asalavei.model.common.Map;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(new Map(10));

        simulation.start();
    }
}