package com.asalavei.view;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.WorldMap;
import com.asalavei.model.entities.Entities;
import com.asalavei.model.entities.Entity;

public class ConsoleRenderer implements Renderer {

    @Override
    public void render(WorldMap map, int turnCounter) {
        int mapSize = map.getSize();
        StringBuilder line = new StringBuilder();

        for (int row = mapSize; row >= 1; row--) {
            for (int column = 1; column <= mapSize; column++) {
                Coordinates coordinates = new Coordinates(row, column);
                Entity entity = map.getEntity(coordinates);

                if (map.isPlaceEmpty(coordinates)) {
                    line.append(Entities.NO_ENTITY.getSprite());
                } else {
                    line.append(entity.getSprite());
                }
            }

            System.out.println(line);
            line.setLength(0);
        }

        System.out.println(turnCounter);
    }
}
