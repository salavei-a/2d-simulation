package com.asalavei.simulation;

public class NullSimulation extends Simulation {

    public NullSimulation() {
        super(null);
    }

    @Override
    public void start() {
        // do nothing
    }

    @Override
    public synchronized void togglePause() {
        // do nothing
    }

    @Override
    public synchronized void stop() {
        // do nothing
    }
}
