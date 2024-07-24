package com.asalavei.model.entities.environment;

import com.asalavei.model.entities.Entities;

public abstract class Resource extends Environment {
    private final int hP;

    protected Resource(Entities entityType, int hP) {
        super(entityType);
        this.hP = hP;
    }

    public int getHP() {
        return hP;
    }
}
