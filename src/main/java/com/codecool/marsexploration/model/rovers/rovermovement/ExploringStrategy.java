package com.codecool.marsexploration.model.rovers.rovermovement;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.List;
import java.util.Random;

public class ExploringStrategy implements MovementStrategy {

    private final CoordinateCalculator coordinateCalculator;
    private final Random random;

    public ExploringStrategy(CoordinateCalculator coordinateCalculator) {
        this.coordinateCalculator = coordinateCalculator;
        this.random = new Random();
    }

    @Override
    public Coordinate decideWhereToGo(Rover rover) {
        MarsMap memory = rover.getMemory();
        Coordinate roverPosition = rover.getCurrentPosition();
        List<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(roverPosition, memory);
        List<Coordinate> availableCoordinates = filterAvailableCoordinates(memory, adjacentCoordinates);

        return !availableCoordinates.isEmpty() ?
                selectRandomCoordinate(availableCoordinates) :
                null;
    }

    private Coordinate selectRandomCoordinate(List<Coordinate> availableCoordinates) {
        int randomIndex = random.nextInt(availableCoordinates.size());
        return availableCoordinates.get(randomIndex);
    }

    private List<Coordinate> filterAvailableCoordinates(MarsMap map, List<Coordinate> coordinates) {
        return coordinates.stream()
                .filter(coordinate -> map.getTileType(coordinate) != TileType.SPACESHIP)
                .toList();
    }
}
