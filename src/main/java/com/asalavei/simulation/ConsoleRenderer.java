package com.asalavei.simulation;

import com.asalavei.simulation.factory.CoordinatesFactory;
import com.asalavei.simulation.entities.Entities;
import com.asalavei.simulation.entities.Entity;

public class ConsoleRenderer implements Renderer {
    @Override
    public void render(WorldMap map, int turnCounter) {
        int mapSize = map.getSize();
        StringBuilder line = new StringBuilder();

        for (int row = mapSize; row >= 1; row--) {
            for (int column = 1; column <= mapSize; column++) {
                Coordinates coordinates = CoordinatesFactory.createCoordinates(row, column);
                Entity entity = map.getEntity(coordinates);

                if (map.isPlaceEmpty(coordinates)) {
                    line.append(Entities.NO_ENTITY.getSprite());
                } else {
                    line.append(entity.getSprite());
                }
            }

            System.out.println(line);
            line.setLength(0);
        }

        System.out.println("Turn: " + turnCounter);
    }

    @Override
    public void printControls() {
        System.out.println("************************************");
        System.out.println("*        SIMULATION CONTROLS       *");
        System.out.println("************************************");
        System.out.println("Keyboard Shortcuts:");
        System.out.println("  's' - Start new simulation");
        System.out.println("  'p' - Pause/Resume");
        System.out.println("  'q' - Quit");
        System.out.println("************************************");
    }

    @Override
    public void printStart() {
        System.out.println("Simulation has started");
    }

    @Override
    public void printEnd() {
        System.out.println("Simulation has ended\n");
    }

    @Override
    public void printStop() {
        System.out.println("Simulation has been stopped\n");
    }

    @Override
    public void printActionUnavailable() {
        System.out.println("No active simulation to pause or resume");
    }

    @Override
    public void printInvalidCommand(String command) {
        System.out.println("Invalid command: " + command);
    }
}
