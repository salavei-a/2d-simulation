package com.asalavei.model.entities.creatures;

import com.asalavei.PathFinder;
import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.Entity;

public abstract class Creature extends Entity {
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

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
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

    public abstract void makeMove(WorldMap map);

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
