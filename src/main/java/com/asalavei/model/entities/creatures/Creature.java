package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entity;

import java.util.*;

public abstract class Creature extends Entity {
    private final int speed;
    protected int hP;
    protected Coordinates coordinates;

    protected Creature(Coordinates coordinates) {
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

    public abstract void makeMove(WorldMap map);

    protected void moveToEntity(WorldMap map) {
        Coordinates newCoordinates = map.getPlaceToMove(this);

        map.removeEntity(coordinates);
        map.setEntity(newCoordinates, this);
        coordinates = newCoordinates;
    }

    public List<Coordinates> getAvailableMovePlaces(int speed) {
        int row = coordinates.getRow();
        int column = coordinates.getColumn();

        List<Coordinates> availableCoordinates = new ArrayList<>();

        for (int i = -speed; i <= speed; i++) {
            for (int j = -speed; j <= speed; j++) {
                availableCoordinates.add(new Coordinates(row + i, column + j));
            }
        }

        return availableCoordinates;
    }

    public boolean isDead() {
        return this.hP <= 0;
    }
}
