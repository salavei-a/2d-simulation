package com.asalavei.model.entities;

import static com.asalavei.Main.RANDOM;

public enum Entities {
    PREDATOR("🦁"),
    HERBIVORE("🐐"),
    ROCK("🪨"),
    GRASS("🌿"),
    NO_ENTITY("⬛");

    private final String sprite;

    Entities(String sprite) {
        this.sprite = sprite;
    }

    public String getSprite() {
        return sprite;
    }

    public static Entities getRandomEntity() {
        Entities entities;

        do {
            entities = values()[RANDOM.nextInt(values().length)];
        }
        while (entities == NO_ENTITY);

        return entities;
    }

    public static String getEntitySprite(Entity entity) {
        return Entities.valueOf(entity.getClass().getSimpleName().toUpperCase()).getSprite();
    }
}
