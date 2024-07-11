package com.asalavei.model.entities.creatures;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.entities.Entity;

public abstract class Creature extends Entity {
    private int speed;
    private int hP;
    private Coordinates coordinates;

    protected Creature(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.speed = 1;
        this.hP = 50;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    protected abstract void makeMove();
}
