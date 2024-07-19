package com.asalavei.model.entities;

public abstract class Entity {
    private final Entities entityType;

    protected Entity(Entities entityType) {
        this.entityType = entityType;
    }

    public String getSprite() {
        return entityType.getSprite();
    }
}
