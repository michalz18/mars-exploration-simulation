package com.codecool.marsexploration.model.rovers.rovermovement;

import com.codecool.marsexploration.CommonMarsMapBuilder;
import com.codecool.marsexploration.MapDisplay;
import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.*;

public class ExploringStrategy implements MovementStrategy {

    private final CoordinateCalculator coordinateCalculator;
    private final Random random;
    private MapDisplay mapDisplay;
    private final Set<Coordinate> visitedPositions = new HashSet<>();
    private final CommonMarsMapBuilder commonMarsMapBuilder;


    public ExploringStrategy(CoordinateCalculator coordinateCalculator, CommonMarsMapBuilder commonMarsMapBuilder) {
        this.coordinateCalculator = coordinateCalculator;
        this.commonMarsMapBuilder = commonMarsMapBuilder;
        this.random = new Random();
    }

    @Override
    public Coordinate decideWhereToGo(Rover rover, SimulationContext simulationContext) {


        Map<Coordinate, TileType> commonMarsMapBuild = commonMarsMapBuilder.buildCommonMarsMap(rover, simulationContext);
        simulationContext.setCommonMarsMap(commonMarsMapBuild);

        MarsMap memory = rover.getMemory();
        MarsMap marsMap1 = new MarsMap(commonMarsMapBuild, memory.getMapDimension());

        Coordinate roverPosition = rover.getCurrentPosition();
        List<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(roverPosition, rover.getMemory());
        List<Coordinate> availableCoordinates = filterAvailableCoordinates(memory, adjacentCoordinates);


        if (mapDisplay == null) {
            mapDisplay = MapDisplay.getInstance(marsMap1);
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