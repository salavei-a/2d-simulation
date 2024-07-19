package com.asalavei.model.entities.environment;

import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.Entity;

public abstract class Environment extends Entity {
    protected Environment(Entities entityType) {
        super(entityType);
    }
}
