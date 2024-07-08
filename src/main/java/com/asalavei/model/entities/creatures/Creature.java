package com.asalavei.model.entities.creatures;

import com.asalavei.model.entities.Entity;

public abstract class Creature extends Entity {
    private int speed;
    private int hP;

    public Creature() {
        this.speed = 1;
        this.hP = 50;
    }

    protected abstract void makeMove();
}
