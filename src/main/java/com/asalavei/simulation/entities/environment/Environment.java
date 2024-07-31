package com.asalavei.simulation.entities.environment;

import com.asalavei.simulation.entities.Entities;
import com.asalavei.simulation.entities.Entity;

public abstract class Environment extends Entity {
    protected Environment(Entities entityType) {
        super(entityType);
    }
}
