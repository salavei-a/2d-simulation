package com.asalavei.simulation.entities.creatures;

import com.asalavei.simulation.entities.Entities;
import com.asalavei.simulation.Coordinates;
import com.asalavei.simulation.WorldMap;

import java.util.Map;

public class Predator extends Creature<Herbivore> {
    private final int attackDamage;

    public Predator(Coordinates coordinates) {
        super(coordinates, 1, Entities.PREDATOR);
        this.attackDamage = 5;
    }

    @Override
    protected void interactWithEntity(Map.Entry<Coordinates, Herbivore> targetNearby, WorldMap map) {
        attack(targetNearby.getValue());
    }

    private void attack(Herbivore herbivore) {
        herbivore.decreaseHP(attackDamage);
    }

    @Override
    protected Class<Herbivore> getTargetClass() {
        return Herbivore.class;
    }
}
