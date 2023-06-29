package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.ClosestResourceCalculator;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.tiletype.TileType;

public class AnaliseBaseResourceCollection implements Action {

    private final Action pickUpResource;
    private final TileType resourceNeededToBuildBase;
    ClosestResourceCalculator resourceFinder;

    public AnaliseBaseResourceCollection(Action pickUpResource, TileType resourceNeededToBuildBase, ClosestResourceCalculator closestResourceCalculator) {
        this.pickUpResource = pickUpResource;
        this.resourceNeededToBuildBase = resourceNeededToBuildBase;
        this.resourceFinder = closestResourceCalculator;
    }

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        if (!didIStayOnResource(rover)) {
            Coordinate wantedCoordinate = resourceFinder.findClosestResource(rover, resourceNeededToBuildBase);
            if (wantedCoordinate != null) {
                setMovingStrategyToDestination(rover, wantedCoordinate);
            }
            rover.setCurrentActivityAssigned(null);
        } else {
            rover.setCurrentActivityAssigned(pickUpResource);
        }
    }

    private boolean didIStayOnResource(Rover rover) {
        return rover.getCurrentPosition().equals(rover.getDestination());
    }

    private void setMovingStrategyToDestination(Rover rover, Coordinate destination) {
        rover.setCurrentMovementStrategyType(MovementStrategyType.MOVING_TO_A_DESTINATION_COORDINATE);
        rover.setDestination(destination);
    }
}
