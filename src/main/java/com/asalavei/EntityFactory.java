package com.asalavei;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.entities.Entity;
import com.asalavei.model.entities.creatures.Herbivore;
import com.asalavei.model.entities.creatures.Predator;
import com.asalavei.model.entities.environment.Grass;
import com.asalavei.model.entities.environment.Rock;

import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.environment.Tree;

public class EntityFactory {

    public Entity createEntity(Entities entities, Coordinates coordinates) {
        switch (entities) {
            case PREDATOR:
                return new Predator(coordinates);

            case HERBIVORE:
                return new Herbivore(coordinates);

            case ROCK:
                return new Rock();

            case GRASS:
                return new Grass();

            case TREE:
                return new Tree();

            default:
                throw new IllegalArgumentException("Unknown entity: " + entities);
        }
    }
}
