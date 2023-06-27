package com.codecool.marsexploration.exploration.logic.deployer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.model.base.Base;
import com.codecool.marsexploration.model.map.MarsMap;
import com.codecool.marsexploration.model.rovers.Rover;

import java.util.List;
import java.util.Random;

public class RoverDeployer {
    private final CoordinateCalculator coordinateCalculator;

    public RoverDeployer(CoordinateCalculator coordinateCalculator) {
        this.coordinateCalculator = coordinateCalculator;
    }

    public boolean landRover(MarsMap map, Base base, Rover rover) {
        Coordinate baseCoordinate = base.getPosition();
        int dimension = map.getMapDimension();
        List<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(baseCoordinate, dimension);
        if (adjacentCoordinates.isEmpty()) {
            return false;
        }
        Random random = new Random();
        Coordinate randomCoordinate = adjacentCoordinates.get(random.nextInt(adjacentCoordinates.size()));
        rover.setCurrentPosition(randomCoordinate);
        return true;
    }
}
