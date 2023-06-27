package com.codecool.marsexploration.calculators.model;

import java.util.List;
import java.util.Objects;

public record Coordinate(int x, int y) {
    public Coordinate getUp() {
        return new Coordinate(x, y - 1);
    }

    public Coordinate getDown() {
        return new Coordinate(x, y + 1);
    }

    public Coordinate getRight() {
        return new Coordinate(x + 1, y);
    }

    public Coordinate getLeft() {
        return new Coordinate(x  -1, y);
    }
    public boolean isAdjacent(Coordinate coordinate) {
        List<Coordinate> adjacentCoordinates = List.of(getUp(), getDown(), getLeft(), getRight());
        return adjacentCoordinates.stream().anyMatch(adjacentCoordinate -> adjacentCoordinate.equals(coordinate));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
