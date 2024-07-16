package com.asalavei;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.entities.Entity;
import com.asalavei.model.entities.creatures.Herbivore;
import com.asalavei.model.entities.creatures.Predator;
import com.asalavei.model.entities.environment.Grass;
import com.asalavei.model.entities.environment.Rock;

public class EntityFactory {

    public Entity createEntity(String entity, Coordinates coordinates) {
        switch (entity) {
            case "Predator":
                return new Predator(coordinates);

            case "Herbivore":
                return new Herbivore(coordinates);

            case "Rock":
                return new Rock();

            case "Grass":
                return new Grass();

            default:
                throw new IllegalArgumentException("Unknown entity: " + entity);
        }
    }
}
