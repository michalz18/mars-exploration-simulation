package com.codecool.marsexploration.model.rovers.rovermovement;

import com.codecool.marsexploration.MapDisplay;
import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.*;

public class ExploringStrategy implements MovementStrategy {

    private final CoordinateCalculator coordinateCalculator;
    private final Random random;
    private MapDisplay mapDisplay;
    private final Set<Coordinate> visitedPositions = new HashSet<>();

    public ExploringStrategy(CoordinateCalculator coordinateCalculator) {
        this.coordinateCalculator = coordinateCalculator;
        this.random = new Random();
    }

    @Override
    public Coordinate decideWhereToGo(Rover rover) {
        MarsMap memory = rover.getMemory();
        Map<Coordinate, TileType> map = memory.getMap();
        map.put(rover.getCurrentPosition(), TileType.ROVER);
        MarsMap marsMap1 = new MarsMap(map, memory.getMapDimension());

        Coordinate roverPosition = rover.getCurrentPosition();
        List<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(roverPosition, memory);
        List<Coordinate> availableCoordinates = filterAvailableCoordinates(memory, adjacentCoordinates);

        if (mapDisplay == null) {
            mapDisplay = new MapDisplay(marsMap1);
        }
        mapDisplay.updateMap(marsMap1);

        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return !availableCoordinates.isEmpty() ?
                selectRandomCoordinate(availableCoordinates) :
                null;
    }

    private Coordinate selectRandomCoordinate(List<Coordinate> availableCoordinates) {
        List<Coordinate> unvisitedCoordinates = new ArrayList<>();
        for (Coordinate coordinate : availableCoordinates) {
            if (!visitedPositions.contains(coordinate)) {
                unvisitedCoordinates.add(coordinate);
            }
        }
        if (!unvisitedCoordinates.isEmpty()) {
            int randomIndex = random.nextInt(unvisitedCoordinates.size());
            Coordinate selectedCoordinate = unvisitedCoordinates.get(randomIndex);
            visitedPositions.add(selectedCoordinate);
            return selectedCoordinate;
        } else {
            int randomIndex = random.nextInt(availableCoordinates.size());
            return availableCoordinates.get(randomIndex);
        }
    }

    private List<Coordinate> filterAvailableCoordinates(MarsMap map, List<Coordinate> coordinates) {
        return coordinates.stream()
                .filter(coordinate -> map.getTileType(coordinate) == TileType.EMPTY)
                .toList();
    }


}