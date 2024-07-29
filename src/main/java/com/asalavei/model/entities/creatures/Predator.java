package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;

import java.util.Map;

public class Predator extends Creature<Herbivore> {
    private int attackDamage;

    public Predator(Coordinates coordinates) {
        super(coordinates, Entities.PREDATOR);
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
