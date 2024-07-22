package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.Entity;

import java.util.Set;
import java.util.stream.Collectors;

public class Predator extends Creature {
    private int attackDamage;

    public Predator(Coordinates coordinates) {
        super(coordinates, Entities.PREDATOR);
        this.attackDamage = 5;
    }

    @Override
    public void makeMove(WorldMap map) {
        Set<Entity> herbivoreNearby = map.getEntitiesNearby(coordinates).values()
                .stream()
                .filter(entity -> entity instanceof Herbivore)
                .collect(Collectors.toSet());

        if (!herbivoreNearby.isEmpty()) {
            attack(herbivoreNearby.iterator().next());
        } else {
            moveToEntity(map);
        }
    }

    private void attack(Entity entity) {
        if (entity instanceof Herbivore herbivore) {
            herbivore.decreaseHP(attackDamage);
        }
    }
}
