package com.codecool.marsexploration.exploration.action;

import com.codecool.marsexploration.exploration.model.SimulationContext;
import com.codecool.marsexploration.model.rovers.Rover;
import com.codecool.marsexploration.tiletype.TileType;

public class PickUpResource implements Action {
    @Override
    public void takeAction(Rover rover, SimulationContext simulationContext) {
        TileType tileType = simulationContext.getMarsMap().getTileType(rover.getCurrentPosition());

        rover.setInventory(tileType);

        simulationContext.getMarsMap().setTileType(rover.getCurrentPosition(), TileType.EMPTY);

        rover.setCurrentActivityAssigned(null);
    }
}
