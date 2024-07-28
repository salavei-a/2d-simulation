package com.asalavei.model.entities;

import com.asalavei.model.entities.creatures.Herbivore;
import com.asalavei.model.entities.creatures.Predator;
import com.asalavei.model.entities.environment.Flower;
import com.asalavei.model.entities.environment.Grass;
import com.asalavei.model.entities.environment.Rock;
import com.asalavei.model.entities.environment.Tree;

public enum Entities {
    PREDATOR("🦁", 0.04, Predator.class),
    HERBIVORE("🐐", 0.06, Herbivore.class),
    GRASS("🌿", 0.1, Grass.class),
    FLOWER("🌷", 0.1, Flower.class),
    ROCK("🪨", 0.05, Rock.class),
    TREE("🌴", 0.05, Tree.class),
    NO_ENTITY("⬛", 0, null);

    private final String sprite;
    private final double spawnRate;
    private final Class<? extends Entity> entityClass;

    Entities(String sprite, double spawnRate, Class<? extends Entity> entityClass) {
        this.sprite = sprite;
        this.spawnRate = spawnRate;
        this.entityClass = entityClass;
    }

    public String getSprite() {
        return sprite;
    }

    public double getSpawnRate() {
        return spawnRate;
    }

    public Class<? extends Entity> getEntityClass() {
        return entityClass;
    }
}
