package com.asalavei.view;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entity;

public class MapConsoleRenderer implements Renderer {

    public void render(WorldMap map, Entity entity) {
        int mapSize = map.getSize();

        for (int row = mapSize; row >= 1; row--) {
            String line = "";
            for (int column = mapSize; column >= 1; column--) {
                Coordinates coordinates = new Coordinates(row, column);

                if (map.isSquareEmpty(coordinates)) {
                    line += "⬛";
                } else {
                    line += "" + getEntitySprite(map.getEntity(coordinates)) + "";
                }
            }

            System.out.println(line);
        }
    }

    @Override
    public void render(WorldMap map) {
        render(map, null);
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
