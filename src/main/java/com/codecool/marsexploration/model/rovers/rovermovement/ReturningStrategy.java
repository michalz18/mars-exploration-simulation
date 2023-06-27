package com.codecool.marsexploration.model.rovers.rovermovement;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Map;
import java.util.Random;

public class ReturningStrategy implements MovementStrategy {
    private final Random random = new Random();

    @Override
    public Coordinate decideWhereToGo(Rover rover) {
        MarsMap memory = rover.getMemory();
        Coordinate basePosition = findPosition(memory.getMap(), TileType.SPACESHIP);
        return findEmptySpot(memory, basePosition);
    }

    private Coordinate findEmptySpot(MarsMap memory, Coordinate basePosition) {
        Coordinate newCoordinate;
        do {

            newCoordinate = getNewRandomCoordinate(basePosition);
        } while (!fieldIsValid(memory.getMap(), newCoordinate));

        return newCoordinate;
    }

    private boolean fieldIsValid(Map<Coordinate, TileType> map, Coordinate newCoordinate) {
        TileType tileType = map.get(newCoordinate);
        return tileType != null && tileType.equals(TileType.EMPTY);
    }

    private Coordinate getNewRandomCoordinate(Coordinate basePosition) {
        int newX = basePosition.x() + random.nextInt(0, 2);
        int newY = basePosition.y() + random.nextInt(0, 2);

        return new Coordinate(newX, newY);
    }

    private Coordinate findPosition(Map<Coordinate, TileType> map, TileType tileType) {
        return map.entrySet()
                .stream()
                .filter(e -> e.getValue().equals(tileType))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

}
