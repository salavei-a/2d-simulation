package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entity;

import java.util.List;

public class Predator extends Creature {
    private int attackDamage;

    public Predator(Coordinates coordinates) {
        super(coordinates);
        this.attackDamage = 5;
    }

    @Override
    public void makeMove(WorldMap map) {
        List<Entity> herbivoreNearby = map.getEntitiesNearby(coordinates, this).values()
                .stream()
                .filter(entity -> entity instanceof Herbivore)
                .toList();

        if (!herbivoreNearby.isEmpty()) {
            attack(herbivoreNearby.getFirst());
        } else {
            moveToEntity(map);
        }
    }

    private void attack(Entity entity) {
        // TODO
    }
}
