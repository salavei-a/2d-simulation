package com.asalavei.view;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entity;

public class ConsoleRenderer implements Renderer {

    @Override
    public void render(WorldMap map, int turnCounter) {
        int mapSize = map.getSize();

        for (int row = mapSize; row >= 1; row--) {
            StringBuilder line = new StringBuilder();
            for (int column = 1; column <= mapSize; column++) {
                Coordinates coordinates = new Coordinates(row, column);

                if (map.isPlaceEmpty(coordinates)) {
                    line.append("⬛");
                } else {
                    line.append(getEntitySprite(map.getEntity(coordinates)));
                }
            }

            System.out.println(line);
        }

        System.out.println(turnCounter);
    }

    private String getEntitySprite(Entity entity) {
        return selectUnicodeSpriteForEntity(entity);
    }

    private String selectUnicodeSpriteForEntity(Entity entity) {
        switch (entity.getClass().getSimpleName()) {
            case "Predator":
                return "🦁";

            case "Herbivore":
                return "🐐";

            case "Rock":
                return "🪨";

            case "Grass":
                return "🌿";

            default:
                throw new IllegalArgumentException(("Unknown entity: " + entity));
        }
    }
}
