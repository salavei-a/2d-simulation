package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.entities.Entity;

import java.util.*;

public abstract class Creature extends Entity {
    private int speed;
    private int hP;
    private Coordinates coordinates;

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

    protected abstract void makeMove();

    public List<Coordinates> getAvailableMovePlaces(int speed) {
        int row = coordinates.getRow();
        int column = coordinates.getColumn();
        
        List<Coordinates> availableCoordinates = new ArrayList<>();

        for (int i = -speed; i <= speed ; i++) {
            for (int j = -speed; j <= speed ; j++) {
                availableCoordinates.add(new Coordinates(row + i, column + j));
            }
        }

        return availableCoordinates;
    }
}
