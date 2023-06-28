package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.model.rovers.rovermovement.MovingToADestinationCoordinate;
import com.codecool.marsexploration.tiletype.TileType;

import java.util.Map;

public class AnaliseGatheringResources implements Action {

    private final TileType resourceToGatherInBase;

    public AnaliseGatheringResources(TileType resourceToGatherInBase) {
        this.resourceToGatherInBase = resourceToGatherInBase;
    }

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        TileType currentTile = simulationContext.getMarsMap().getTileType(rover.getCurrentPosition());

        if (isFindingResource(rover, currentTile)) {
            handleFindingResource(rover);
        } else if (isPickingUpResource(rover, currentTile)) {
            handleResourcePickUp(rover);
        } else if (isMovingToBase(rover)) {
            handleMovingToBase(rover);
        } else if (isPassingResourceToBase(rover)) {
            handleResourcePass(rover);
        }
    }

    private boolean isFindingResource(Rover rover, TileType currentTile) {
        return rover.getInventory() == null && !currentTile.equals(resourceToGatherInBase);
    }

    private boolean isPickingUpResource(Rover rover, TileType currentTile) {
        return rover.getInventory() == resourceToGatherInBase && currentTile.equals(resourceToGatherInBase);
    }

    private boolean isMovingToBase(Rover rover) {
        return rover.getInventory() == resourceToGatherInBase && !rover.getCurrentPosition().equals(rover.getBase().getPosition());
    }

    private boolean isPassingResourceToBase(Rover rover) {
        return rover.getInventory() == resourceToGatherInBase && rover.getCurrentPosition().equals(rover.getBase().getPosition());
    }

    private void handleResourcePass(Rover rover) {
        rover.setCurrentActivityAssigned(new PassResourceToBase());
    }

    private void handleMovingToBase(Rover rover) {
        rover.setDestination(rover.getBase().getPosition());
        rover.setCurrentMovementStrategyType(MovementStrategyType.MOVING_TO_A_DESTINATION_COORDINATE);
        rover.setCurrentActivityAssigned(null);
    }

    private void handleResourcePickUp(Rover rover) {
        rover.setCurrentActivityAssigned(new PickUpResource());
    }

    private void handleFindingResource(Rover rover) {
        Coordinate closestWantedResource = findClosestResource(resourceToGatherInBase, rover);
        rover.setDestination(closestWantedResource);
        rover.setCurrentMovementStrategyType(MovementStrategyType.MOVING_TO_A_DESTINATION_COORDINATE);
        rover.setCurrentActivityAssigned(null);
    }

    private Coordinate findClosestResource(TileType resource, Rover rover) {
        Coordinate currentPosition = rover.getCurrentPosition();
        int closestDistance = Integer.MAX_VALUE;
        Coordinate closestCoordinate = null;

        for (Map.Entry<Coordinate, TileType> entry : rover.getMemory().getMap().entrySet()) {
            if (entry.getValue() == resource) {
                int distance = calculateDistance(currentPosition, entry.getKey());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestCoordinate = entry.getKey();
                }
            }
        }
        return closestCoordinate;
    }

    private int calculateDistance(Coordinate from, Coordinate to) {
        int dx = from.x() - to.x();
        int dy = from.x() - to.y();

        return (int) Math.sqrt(dx * dy + dy * dy);
    }
}
