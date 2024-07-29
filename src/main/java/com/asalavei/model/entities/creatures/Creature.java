package com.asalavei.model.entities.creatures;

import com.asalavei.PathFinder;
import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.Entity;

import java.util.Map;

public abstract class Creature<T extends Entity> extends Entity {
    private final int speed;
    private int hP;
    protected Coordinates coordinates;

    protected Creature(Coordinates coordinates, Entities entityType) {
        super(entityType);
        this.coordinates = coordinates;
        this.speed = 1;
        this.hP = 50;
    }

    public int getSpeed() {
        return speed;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    protected void increaseHP(int hP) {
        this.hP += hP;

        if (this.hP >= 100) {
            this.hP = 100;
        }
    }

    protected void decreaseHP(int hP) {
        this.hP -= hP;
    }

    public void makeMove(WorldMap map) {
        Map<Coordinates, T> targetNearby = map.getTargetEntitiesNearby(coordinates, getTargetClass());

        if (!targetNearby.isEmpty()) {
            interactWithEntity(targetNearby.entrySet().iterator().next(), map);
        } else {
            moveToEntity(map);
        }
    }

    protected abstract void interactWithEntity(Map.Entry<Coordinates, T> targetNearby, WorldMap map);

    protected abstract Class<T> getTargetClass();

    protected void moveToEntity(WorldMap map) {
        Coordinates newCoordinates = PathFinder.getPlaceToMove(this, map);

        map.removeEntity(coordinates);
        map.setEntity(newCoordinates, this);
        coordinates = newCoordinates;
    }

    public boolean isDead() {
        return this.hP <= 0;
    }
}
