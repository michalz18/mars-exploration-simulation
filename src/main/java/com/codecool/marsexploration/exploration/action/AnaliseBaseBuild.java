package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.model.rovers.rovermovement.MovementStrategyType;
import com.codecool.marsexploration.tiletype.TileType;

public class AnaliseBaseBuild implements Action {

    private final Action baseBuilding;

    public AnaliseBaseBuild(Action baseBuilding) {
        this.baseBuilding = baseBuilding;
    }

    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        TileType check = null;
        if (rover.getDestination() != null) {
            check = simulationContext.getMarsMap().getTileType(rover.getDestination());
        }
        System.out.println(check);
        rover.setDestination(rover.getBase().getPosition());
        rover.setCurrentMovementStrategyType(MovementStrategyType.MOVING_TO_A_DESTINATION_COORDINATE);
        if (rover.getCurrentPosition().equals(rover.getBase().getPosition())) {
            rover.setCurrentActivityAssigned(baseBuilding);
        } else {
            rover.setCurrentActivityAssigned(null);
        }
    }
}
