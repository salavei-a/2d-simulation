package com.asalavei.model.common;

import java.util.Objects;

import static com.asalavei.Main.RANDOM;

public class Coordinates {
    private final Integer row;
    private final Integer column;

    public Coordinates(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public static Coordinates getRandomCoordinates(WorldMap map) {
        return new Coordinates(RANDOM.nextInt(map.getSize()) + 1, RANDOM.nextInt(map.getSize()) + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(row, that.row) && Objects.equals(column, that.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
