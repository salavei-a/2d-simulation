package com.asalavei.model.entities;

import static com.asalavei.Main.RANDOM;

public enum Entities {
    PREDATOR("🦁"),
    HERBIVORE("🐐"),
    ROCK("🪨"),
    GRASS("🌿"),
    TREE("🌴"),
    NO_ENTITY("⬛");

    private final String sprite;

    Entities(String sprite) {
        this.sprite = sprite;
    }

    public String getSprite() {
        return sprite;
    }

    public static Entities getRandomEntityType() {
        Entities entities;

        do {
            entities = values()[RANDOM.nextInt(values().length)];
        }
        while (entities == NO_ENTITY);

        return entities;
    }
}
