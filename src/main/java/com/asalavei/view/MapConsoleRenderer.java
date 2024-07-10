package com.asalavei.view;

import com.asalavei.model.common.Coordinates;
import com.asalavei.model.common.Map;
import com.asalavei.model.entities.Entity;

public class MapConsoleRenderer implements Renderer {

    public void render(Map map, Entity entityToMove) {
        int mapSize = map.getSize();

        for (int row = mapSize; row >= 1; row--) {
            String line = "";
            for (int column = mapSize; column >= 1; column--) {
                Coordinates coordinates = new Coordinates(row, column);

                if (map.isSquareEmpty(coordinates)) {
                    line += " ☐ ";
                } else {
                    line += " ☑ ";
                }
            }

            System.out.println(line);
        }
    }

    @Override
    public void render(Map map) {
        render(map, null);
    }
}
