package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.ClosestResourceCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.base.Status;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.outcome.ExplorationOutcome;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.List;
import java.util.Optional;

public class PlanBase implements Action {
    private final ClosestResourceCalculator resourceFinder;
    private final CoordinateCalculator coordinateCalculator;

    public PlanBase(ClosestResourceCalculator resourceFinder, CoordinateCalculator coordinateCalculator) {
        this.resourceFinder = resourceFinder;
        this.coordinateCalculator = coordinateCalculator;
    }

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        Coordinate suitableCoordinate = resourceFinder.findClosestResource(rover, TileType.WATER);
        List<Coordinate> closeCoordinates = coordinateCalculator.getCoordinatesInRadius(suitableCoordinate, simulationContext.getMarsMap(), 4);
        List<Coordinate> adjacentEmptyCoordinates = coordinateCalculator.getOnlyEmptyCoordinates(closeCoordinates, simulationContext.getMarsMap());
        Optional<Coordinate> randomCoordinate = coordinateCalculator.getRandomCoordinate(adjacentEmptyCoordinates);
        randomCoordinate.ifPresentOrElse(coordinate -> {
            Base newBase = createNewBase(coordinate);
            rover.setBase(newBase);
        }, () -> {
            rover.setExplorationOutcome(ExplorationOutcome.LACK_OF_RESOURCES);
        });
    }

    private Base createNewBase(Coordinate suitableCoordinate) {
        return new Base(Status.PLANNED, suitableCoordinate);
    }

//    private Map<Coordinate, TileType> buildBase(Rover rover, Coordinate suitableCoordinate) {
//        Map<Coordinate, TileType> map = rover.getMemory().getMap();
//        map.put(suitableCoordinate, TileType.BASE);
//        return map;
//    }

}
