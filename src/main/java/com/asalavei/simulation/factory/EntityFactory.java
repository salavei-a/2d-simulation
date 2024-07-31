package com.asalavei.simulation.factory;

import com.asalavei.simulation.entities.creatures.*;
import com.asalavei.simulation.entities.environment.*;
import com.asalavei.simulation.Coordinates;
import com.asalavei.simulation.entities.Entity;
import com.asalavei.simulation.entities.Entities;

public class EntityFactory {

    private EntityFactory() {
    }

    public static Entity createEntity(Entities entities, Coordinates coordinates) {
        return switch (entities) {
            case PREDATOR -> new Predator(coordinates);
            case GOAT -> new Goat(coordinates);
            case HORSE -> new Horse(coordinates);
            case ROCK -> new Rock();
            case GRASS -> new Grass();
            case FLOWER -> new Flower();
            case TREE -> new Tree();
            default -> throw new IllegalArgumentException("Unknown entity: " + entities);
        };
    }
}
