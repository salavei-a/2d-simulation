package com.asalavei.model.entities.environment;

import com.asalavei.model.entities.Entities;

public abstract class Resource extends Environment {
    protected Resource(Entities entityType) {
        super(entityType);
    }
}
