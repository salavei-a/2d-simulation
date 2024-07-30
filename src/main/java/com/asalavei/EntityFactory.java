package com.asalavei;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.entities.Entity;
import com.asalavei.model.entities.creatures.Goat;
import com.asalavei.model.entities.creatures.Horse;
import com.asalavei.model.entities.creatures.Predator;
import com.asalavei.model.entities.environment.*;

import com.asalavei.model.entities.Entities;

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
